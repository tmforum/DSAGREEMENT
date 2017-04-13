package org.tmf.dsmapi.agreement;

/**
 This class serves as the "facade" that provides the persistence context to the Agreement entity
 Requests on the Agreement resource are executed via the AgreementFacade
 **/

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
//import org.tmf.dsmapi.commons.utils.BeanUtils;
import org.tmf.dsmapi.agreement.model.Agreement;
//import org.tmf.dsmapi.agreement.event.AppointmentEventPublisherLocal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.tmf.dsmapi.appointment.model.AppointmentStatusEnum;
//import org.tmf.dsmapi.appointment.model.RelatedObject;
//import org.tmf.dsmapi.appointment.model.RelatedPartyRef;

@Stateless
public class AgreementFacade extends AbstractFacade<Agreement> {
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

	public void checkCreation(Agreement entity) throws BadUsageException, UnknownResourceException {
	}

	/*
	public Agreement patchAttributs(String id, Agreement partialEntity)
		throws UnknownResourceException, BadUsageException {
	}
	*/
}
