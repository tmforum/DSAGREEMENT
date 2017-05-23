package org.tmf.dsmapi.agreementspec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.ObjectMerger;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;
import org.tmf.dsmapi.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.model.AgreementAttachment;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.agreement.model.AgreementStatusEnum;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import org.tmf.dsmapi.commons.utils.BeanUtils;
import org.tmf.dsmapi.event.EventPublisher;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by atinsingh on 4/5/17.
 */
@Stateless
public class AgreementSpecificationFacade extends AbstractFacade<AgreementSpecification> {

    /*
     * get a State model
     */
    StateModelImpl stateModel = new StateModelImpl(AgreementStatusEnum.class);
    @EJB
    EventPublisher<AgreementSpecification> eventPublisher;
    private Logger logger = Logger.getLogger(AgreementSpecificationFacade.class.getName());
    @PersistenceContext(unitName = "DSAgreementPU")
    private EntityManager entityManager;


    public AgreementSpecificationFacade() {
        super(AgreementSpecification.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Function will validate the incoming JSON load for following
     * <p>
     * LifeCycleStatus  = Any new POST creation should have lifecycleStatus as Initialized on null,
     * incase of null, function will set it to Initialized.
     * <p>
     * Function will check the mandatory attribute in the object, if they are missing,
     * function will throw BadUsageException.
     *
     * @param specification
     * @throws BadUsageException
     */

    public void checkCreation(AgreementSpecification specification) throws BadUsageException {


        AgreementSpecification agreementSpecification = null;
        //Check if ID for resource is coming in incoming JSON load
        //if its missing go on and generate one, else try to find a resource with that
        //id to ensure we are not going to create duplicate resource, else JPA will
        //throw and error for violation of Primary key constraint.
        if (specification.getId() == null || specification.getId().equals("")) {
            logger.log(Level.INFO, "AgreementSpecification with auto generated value will posted");
        } else {
            try {
                agreementSpecification = this.find(specification.getId());
                //match found, throw exception that
                if (agreementSpecification != null) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC,
                            "Duplicate Resource Exception - AgreementSpecification with same id : " + specification.getId() + "Already Exists!"
                    );
                }
            } catch (UnknownResourceException ex) {
                logger.log(Level.INFO, "Resource not found and a new resource will be created");
                logger.log(Level.INFO, "AgreementSpecification with id " + specification.getId() + " is being posted");
            }

        }

        //Now I have to create resource
        //if there is no status set to initialized one
        logger.log(Level.INFO, "Checking for LifeCycleStatus now ....");
        if (specification.getLifecycleStatus() == null) {
            logger.log(Level.INFO, "Setting up the LifeCycleStatus as " + AgreementStatusEnum.INITIALIZED.toString());
            specification.setLifecycleStatus(AgreementStatusEnum.INITIALIZED.name());
        } else {
            //if there is status, then it should be initialized.
            if (!specification.getLifecycleStatus().name().equalsIgnoreCase(AgreementStatusEnum.INITIALIZED.name())) {
                throw new BadUsageException(
                        ExceptionType.BAD_USAGE_FLOW_TRANSITION,
                        "AgreementSpecification LifeCycle Status " + specification.getLifecycleStatus().getValue() + "is not the first state"
                );
            }
        }

        //now check the mandatory values
        logger.log(Level.INFO, "Checking for Name as mandatory field now ....");
        //Check for the name
        if (specification.getName() == null || specification.getName().equalsIgnoreCase("")) {
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Name is mandatory"
            );
        }

        //attachment
        logger.log(Level.INFO, "Checking for attachment as mandatory field now ....");
        if (specification.getAttachment() == null || specification.getAttachment().isEmpty()) {
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Atleast 1 attachment is required"
            );
        } else {
            for (AgreementAttachment attachment : specification.getAttachment()) {
                //check mandatory field for attachement;
                if (attachment.getId() == null) {
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Attachment id should be provided"
                    );
                }
                if (attachment.getHref() == null || attachment.getHref().equalsIgnoreCase("")) {
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Attachment HREF should be provided"
                    );
                }
                if (attachment.getType() == null || attachment.getType().equalsIgnoreCase("")) {
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Attachment type should be provided"
                    );
                }
                if (attachment.getUrl() == null || attachment.getUrl().equalsIgnoreCase("")) {
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Attachment URL should be provided"
                    );
                }

            }

            logger.log(Level.INFO, "Checking for Bundle as mandatory field now ....");

            //if isbundle true that bundled product must be there.
            if (specification.getIsBundle() && (specification.getSpecificationRelationship() == null || specification.getSpecificationRelationship().isEmpty())) {
                throw new BadUsageException(
                        ExceptionType.BAD_USAGE_OPERATOR, "If isBundle is true, Specification Relationship should be provided"
                );
            } else {
                logger.log(Level.INFO, "All good now I am going to create the AgreementSpecification Resource ....");
            }

        }

    }


    /**
     * Function will patch the partial entity passed to the object.
     * in patching we have ensure that lifeCycleStatus is not patched by skipping a cycle.
     *
     * @param id
     * @param patchObject
     * @return
     * @throws UnknownResourceException
     * @throws BadUsageException
     */


    public AgreementSpecification patchObject(String id, final AgreementSpecification patchObject) throws UnknownResourceException, BadUsageException {

        AgreementSpecification specification = this.find(id);


        //Verify is this patching is permitted
        verifyStatus(specification, patchObject);
        //verify that only allowed attributes are patched
        checkPatchObject(patchObject);

        patchObject.setId(id);


        //DiffNode root = ObjectDifferBuilder.buildDefault().compare(patchObject, specification);

        ObjectMerger merger = new ObjectMerger();
        final AgreementSpecification head = specification;

        AgreementSpecification agreementSpecification = merger.merge(patchObject,specification,head);

//
//        root.visit(new DiffNode.Visitor()
//        {
//            public void node(DiffNode node, Visit visit)
//            {
//
//                if (node.getState() == DiffNode.State.ADDED)
//                {
//                    node.canonicalSet(head, node.canonicalGet(patchObject));
//                }
//                else if (node.getState() == DiffNode.State.REMOVED)
//                {
//                    node.canonicalUnset(head);
//                }
//                else if (node.getState() == DiffNode.State.CHANGED)
//                {
//                    if (node.hasChildren())
//                    {
//                        node.visitChildren(this);
//                        visit.dontGoDeeper();
//                    }
//                    else
//                    {
//                        node.canonicalSet(head, node.canonicalGet(patchObject));
//                    }
//                }
//            }
//
//
//        });
//        System.out.println(specification);
//        System.out.println("============");
//        System.out.println(head);

        //Create an object mapper
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.convertValue(patchObject, JsonNode.class);
//        final JsonPatch patch = JsonDiff.asJsonPatch(objectMapper.convertValue(patchObject, JsonNode.class)
//                ,objectMapper.convertValue(specification,JsonNode.class));

        //       final JsonMergePatch patch = objectMapper.convertValue(patchObject, JsonMergePatch.class);
//        try {
//            final JsonNode jsonNode = patch.apply(objectMapper.convertValue(specification,JsonNode.class));
//            logger.log(Level.INFO,"-------------");
//            logger.log(Level.INFO,jsonNode.toString());
//            specification = objectMapper.convertValue(jsonNode, AgreementSpecification.class);
//
//        }catch (JsonPatchException ex){
//            logger.log(Level.INFO,ex.getMessage());
//        }


//        if (BeanUtils.patch(specification, patchObject, jsonNode)) {
//            eventPublisher.generateEventNotification(specification, new Date(), AgreementEventEnum.AgreementSpecValueChangeNotification);
//        }
        //this.getEntityManager().persist(agreementSpecification);
        if(agreementSpecification!=null){
            eventPublisher.generateEventNotification(specification, new Date(), AgreementEventEnum.AgreementSpecValueChangeNotification);
        }
        this.edit(agreementSpecification);
        return agreementSpecification;

    }

    /**
     * Check that we are only allowing patchable attributes
     *
     * @param patchObject
     */
    public void checkPatchObject(AgreementSpecification patchObject) throws UnknownResourceException, BadUsageException {
        //Id can't be patched
        if (patchObject.getId() != null) {
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "ID can't be patched"
            );
        }
        /*
        if (patchObject.getHref() != null) {
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "HREF can't be patched"
            );
        }
        if(patchObject.getDescription()!=null){
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "Description can't be patched"
            );
        }
        if(patchObject.getIsBundle()!=null){
            if(patchObject)
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "isBundle can't be patched"
            );
        }
        if(patchObject.getHref()!=null){
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "HREF can't be patched"
            );
        }*/
    }


    /**
     * Verify that lifeCycleStatus passed in the request is as per the transition defined in specification document.
     * for the change in life cycle, we have to publish an event for subscribers of the event.
     */
    public void verifyStatus(AgreementSpecification currentEntity, AgreementSpecification partialEntity) throws BadUsageException {
        if (null != partialEntity.getLifecycleStatus() && !partialEntity.getLifecycleStatus().name().equals(currentEntity.getLifecycleStatus().name())) {
            stateModel.checkTransition(currentEntity.getLifecycleStatus(), partialEntity.getLifecycleStatus());
            eventPublisher.generateEventNotification(currentEntity, new Date(), AgreementEventEnum.AgreementStateChangeNotification);
        }
    }
}
