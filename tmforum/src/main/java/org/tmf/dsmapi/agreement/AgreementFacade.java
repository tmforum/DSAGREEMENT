package org.tmf.dsmapi.agreement;

/**
 * This class serves as the "facade" that provides the persistence context to the Agreement entity
 * Requests on the Agreement resource are executed via the AgreementFacade
 **/

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.danielbechler.diff.ObjectMerger;
import org.tmf.dsmapi.event.EventPublisher;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.agreement.model.Agreement;
import org.tmf.dsmapi.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.model.AgreementStatusEnum;

@Stateless
public class AgreementFacade extends AbstractFacade<Agreement> {
    @EJB
    EventPublisher<Agreement> eventPublisher;
    StateModelImpl stateModel = new StateModelImpl(AgreementStatusEnum.class);
    //EventPublisherInterface<Agreement> eventPublisher;
    @PersistenceContext(unitName = "DSAgreementPU")
    private EntityManager em;

    public AgreementFacade() {
        // Initialize the AbstractFacade to handle the Agreement Class
        super(Agreement.class);
    }

    // Override the abstract method
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // Validate mandatory fields and data types during entity creation
    public void checkCreation(Agreement entity) throws BadUsageException, UnknownResourceException {
        Agreement ag = null;

        // Validate Agreement ID
        if (entity.getId() == null || entity.getId().equals("")) {
            Logger.getLogger(AgreementFacade.class.getName()).
                    log(Level.INFO, "Agreement with auto generated id is being posted");
        } else {
            try {
                ag = this.find(entity.getId());
                if (ag != null) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC,
                            "Duplicate Exception, Agreement with id :" + entity.getId() + " alreay exists");
                }
            } catch (UnknownResourceException ex) {
                //Do nothing create ok
                Logger.getLogger(AgreementFacade.class.getName()).
                        log(Level.INFO, "Agreement with id = " + entity.getId() + " is being posted", ex);
            }
        }

        // Name is mandatory
        if (entity.getName() == null || entity.getName().equals("")) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Agreement Name is mandatory");
        }

        // Engaged Party Role is mandatory
        if (entity.getEngagedPartyRole() == null) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Engaged Party Role is mandatory");
        }

        // Agreement Item is mandatory
        if (entity.getAgreementItem() == null) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "Agreement Item is mandatory");
        }

        // If Completion Date is not provided, default it to today's date
        if (entity.getCompletionDate() == null) {
            entity.setCompletionDate(new Date());
            //entity.setCompletionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            // Add logic to default it to today
        }

        // If Version is not provided, default it to 0
        if (entity.getVersion() == null || entity.getVersion().equals("")) {
            entity.setVersion(Integer.toString(0));
        }
        if(entity.getStatus()==null){
            entity.setStatus(AgreementStatusEnum.INITIALIZED.getValue());;
        }
    }

    // Verify if the entity is patchable and then patch the entity if so.
    // The function generates appropriate events, based on the nature of the update.
    public Agreement patchAttributes(String id, Agreement partialEntity)
            throws UnknownResourceException, BadUsageException {

        // Firstly check if the requested entity exists
        Agreement entity = this.find(id);

        final Agreement head = entity;

        // Verify if this patch is permitted and if so, publish a StatusChange event
        verifyStatus(entity, partialEntity);

        // Verify that only allowed attributes are patched
        checkPatchAttributes(partialEntity);

        partialEntity.setId(id);

        Agreement agreement = new ObjectMerger().merge(partialEntity,entity, head);

        // Generate a JSON object from the given partial entity
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.convertValue(partialEntity, JsonNode.class);

        // May need to use edit() should BeanUtils not work
        // Seems like the entity is not attached to the EntityManager scope at all!

        // Validate if the patch will actually result in the entity values getting updated.
        // If yes, then publish a Change notification and update the entity.
        // The BeanUtils.patch will copy the input attribute values to the entity, if values differ.
//        if (BeanUtils.patch(entity, partialEntity, jsonNode)) {
//
//
//        }
//
        this.edit(agreement);
        if(agreement!=null){
            eventPublisher.generateEventNotification(entity, new Date(),
                    AgreementEventEnum.AgreementValueChangeNotification);
        }


        return agreement;
    }

    public void checkPatchAttributes(Agreement patchEntity)
            throws UnknownResourceException, BadUsageException {

        // Remove attributes that are not patchable
        patchEntity.setId(null);
        patchEntity.setHref(null);
        patchEntity.setInitialDate(null);
    }

    /**
     * Verify that lifeCycleStatus passed in the request is allowed and if so, 
     * publish an event for subscribers of the event.
     **/

    public void verifyStatus(Agreement currentEntity, Agreement partialEntity)
            throws BadUsageException {

        int statusChanged = 0;

        if (null != partialEntity.getStatus() &&
                !partialEntity.getStatus().equals(currentEntity.getStatus())) {

            // Check if the status change transition is permitted
            stateModel.checkTransition(currentEntity.getStatus(), partialEntity.getStatus());

            //publisher.statusChangedNotification(currentEntity, new Date());
            eventPublisher.generateEventNotification(currentEntity, new Date(),
                    AgreementEventEnum.AgreementStateChangeNotification);
        }
    }
}
