package org.tmf.dsmapi.agreement;

/**
 This class serves as the "facade" that provides the persistence context to the Agreement entity
 Requests on the Agreement resource are executed via the AgreementFacade
 **/

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tmf.dsmapi.commons.utils.BeanUtils;
import org.tmf.dsmapi.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.model.AgreementStatusEnum;

@Stateless
public class AgreementFacade extends AbstractFacade<Agreement> {
	@PersistenceContext(unitName = "DSAgreementPU")
	private EntityManager em;

    @EJB
	EventPublisher<Agreement> eventPublisher;
    //EventPublisherInterface<Agreement> eventPublisher;

	StateModelImpl stateModel = new StateModelImpl(AgreementStatusEnum.class);

	public AgreementFacade() {
		// Initialize the AbstractFacade to handle the Agreement Class
		super(Agreement.class);
	}

	// Override the abstract method
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	// Validate mandatory fields and data types
	public void checkCreation(Agreement entity) throws BadUsageException, UnknownResourceException {
		Agreement ag = null;

		// Validate Agreement ID
		if (entity.getId() == null || entity.getId().isEmpty()) {
			Logger.getLogger(AgreementFacade.class.getName()).
				log(Level.INFO, "Agreement with autogenerated id is being posted");
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
		if (entity.getName() == null || entity.getId().isEmpty()) {
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
		if (entity.getVersion() == null || entity.getVersion().isEmpty()) {
			entity.setVersion(Integer.toString(0));
		}
	}

    public Agreement patchAttributes(String id, Agreement partialEntity) 
		throws UnknownResourceException, BadUsageException {

		// Firstly check if the requested entity exists
        Agreement entity = this.find(id);

        if(entity == null){
            throw new UnknownResourceException(ExceptionType.UNKNOWN_RESOURCE,
				"Resource with ID " + id + " cannot be located");
        }

		partialEntity.setId(id);

        // Verify if this patch is permitted and if so, publish a StatusChange event
        verifyStatus(entity, partialEntity);

        // Verify that only allowed attributes are patched
        checkPatchAttributes(partialEntity);
        //patchObject.setId(id);

        // Generate a JSON object from the given partial entity
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(partialEntity, JsonNode.class);

		// May need to use edit() should BeanUtils not work
		// Seems like the entity is not attached to the EntityManager scope at all!
        //this.edit(specification);

        if(BeanUtils.patch(entity, partialEntity, jsonNode)) {
			eventPublisher.generateEventNotification(entity, new Date(), 
				AgreementEventEnum.AgreementValueChangeNotification);
        }

        return entity;
    }

	/*
	public Agreement patchAttributs(String id, Agreement partialEntity)
		throws UnknownResourceException, BadUsageException {
	}
	*/

	public void checkPatchAttributes(Agreement patchEntity) 
		throws UnknownResourceException, BadUsageException {

		if (null != patchEntity.getId()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, "id is not patchable");
        }
        if (null != patchEntity.getHref()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, "href is not patchable");
        }
        if (null != patchEntity.getInitialDate()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, "initialDate is not patchable");
        }
	}


    /**
     * Verify that lifeCycleStatus passed in the request is allowed and if so, 
     * publish an event for subscribers of the event.
    **/

    public void verifyStatus(Agreement currentEntity, Agreement partialEntity) 
		throws BadUsageException {

		int statusChanged = 0;

		if(null != partialEntity.getStatus() && 
			!partialEntity.getStatus().equals(currentEntity.getStatus())) {

			// Check if the status change transition is permitted
            stateModel.checkTransition(currentEntity.getStatus(), partialEntity.getStatus());

            //publisher.statusChangedNotification(currentEntity, new Date());
			eventPublisher.generateEventNotification(currentEntity, new Date(), 
				AgreementEventEnum.AgreementStateChangeNotification);
        }
    }
}
