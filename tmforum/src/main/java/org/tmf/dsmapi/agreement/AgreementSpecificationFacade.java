package org.tmf.dsmapi.agreement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tmf.dsmapi.agreement.model.AgreementAttachment;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.agreement.model.AgreementStatusEnum;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import org.tmf.dsmapi.commons.utils.BeanUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by atinsingh on 4/5/17.
 */
@Stateless
public class AgreementSpecificationFacade extends AbstractFacade<AgreementSpecification> {

    private Logger logger = Logger.getLogger(AgreementSpecificationFacade.class.getName());

    public AgreementSpecificationFacade(Class<AgreementSpecification> entityClass) {
        super(entityClass);
    }

    @PersistenceContext(unitName = "DSAgreementPU")
    private EntityManager entityManager;

    /*
     * get a State model
     */
    StateModelImpl stateModel = new StateModelImpl(AgreementStatusEnum.class);


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Going to create Entity AgreementSpefication Based on the Resource provided
     * @param specification
     * @throws BadUsageException
     * @throws UnknownResourceException
     */

    public void checkCreation(AgreementSpecification specification) throws BadUsageException, UnknownResourceException {

        //Create a new entity object
        AgreementSpecification agreementSpecification = null;

        if(specification.getId()==null||specification.getId().equals("")){

            logger.log(Level.INFO,"AgreementSpecification with auto generated value will posted");
        }else {
            try {
                agreementSpecification = this.find(specification.getId());
                //match found, throw exception that
                if(agreementSpecification!=null){
                    throw  new BadUsageException(ExceptionType.BAD_USAGE_GENERIC,
                            "Duplicate Resource Exception - AgreementSpecification with same id : "+specification.getId()+"Already Exists!"
                            );
                }
            }catch (UnknownResourceException ex){
                logger.log(Level.INFO, "Resource not found and a new resouce will be created");
                logger.log(Level.INFO, "AgreementSpecification with id "+specification.getId()+"is being posted",ex);
            }

        }

        //Now I have to create resource
        //if there is no status set to initialized one
        if(specification.getLifecycleStatus()==null){
            specification.setLifeCycleStatus(AgreementStatusEnum.INITIALIZED);
        }else {
            //if there is status, then it should be initialized.
            if(!specification.getLifecycleStatus().name().equalsIgnoreCase(AgreementStatusEnum.INITIALIZED.name())){
                throw new BadUsageException(
                  ExceptionType.BAD_USAGE_FLOW_TRANSITION,
                        "AgreementSpecification LifeCycle Status "+specification.getLifecycleStatus().getValue()+"is not the first state"
                );
            }
        }

        //now check the mandatory values

        //name
        if(specification.getName()==null||specification.getName().equalsIgnoreCase("")){
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Name is mandatory"
            );
        }

        //attachment
        if(specification.getAttachment()==null||specification.getAttachment().isEmpty()){
            throw new BadUsageException(
              ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Atleast 1 attachment is required"
            );
        }else{
            for(AgreementAttachment attachment : specification.getAttachment()){
                //check mandatory field for attachement;
                if(attachment.getId()==null){
                    throw new BadUsageException(
                      ExceptionType.BAD_USAGE_MANDATORY_FIELDS,"Attachment id should be provided"
                    );
                }
                if(attachment.getHref()==null||attachment.getHref().equalsIgnoreCase("")){
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS,"Attachment HREF should be provided"
                    );
                }
                if(attachment.getType()==null||attachment.getType().equalsIgnoreCase("")){
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS,"Attachment type should be provided"
                    );
                }
                if(attachment.getUrl()==null||attachment.getUrl().equalsIgnoreCase("")){
                    throw new BadUsageException(
                            ExceptionType.BAD_USAGE_MANDATORY_FIELDS,"Attachment URL should be provided"
                    );
                }

            }



            //if isbundle true that bundled product must be there.
            if(specification.getBundle()&&(specification.getSpecificationRelationship()==null||specification.getSpecificationRelationship().isEmpty())){
                throw new BadUsageException(
                        ExceptionType.BAD_USAGE_OPERATOR,"If isBundle is true, Specification Relationship should be provided"
                );
            }

        }

    }
    /**
     *
     *
     *
     */
    //patch attribute
    public AgreementSpecification patchObject(String id, AgreementSpecification patchObject) throws UnknownResourceException, BadUsageException {
        AgreementSpecification specification = this.find(id);
        if(specification==null){
            throw  new UnknownResourceException(
                    ExceptionType.UNKNOWN_RESOURCE,
                    "Resource with ID "+id+"can't be located"

            );
        }
        //Verify is this patching is permitted
        verifyStatus(specification,patchObject);
        //verify that only allowed attributes are patched
        checkPathObject(patchObject);
        patchObject.setId(id);
        //Create an object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(patchObject,JsonNode.class);
        if (BeanUtils.patch(specification, patchObject, jsonNode)) {
            //publisher.valueChangedNotification(currentEntity, new Date());
        }

        return specification;

    }

    /**
     * Check that we are only allowing patchable attributes
     * @param patchObject
     */
    public void checkPathObject(AgreementSpecification patchObject) throws UnknownResourceException, BadUsageException{
        //Id can't be patched
        if(patchObject.getId()!=null){
            throw new BadUsageException(
                    ExceptionType.BAD_USAGE_OPERATOR,
                    "ID can't be patched"
                    );
        }
        if(patchObject.getHref()!=null){
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
        if(patchObject.getBundle()!=null){
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
        }
    }


    /**
     *
     *
     */
    public void verifyStatus(AgreementSpecification currentEntity, AgreementSpecification partialEntity) throws BadUsageException {
        if (null != partialEntity.getLifecycleStatus() && !partialEntity.getLifecycleStatus().name().equals(currentEntity.getLifecycleStatus().name())) {
            stateModel.checkTransition(currentEntity.getLifecycleStatus(), partialEntity.getLifecycleStatus());
            //publisher.statusChangedNotification(currentEntity, new Date());
        }
    }
}
