package org.tmf.dsmapi.agreement.event;

import org.apache.commons.logging.Log;
import org.tmf.dsmapi.agreement.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.event.EventPublisher;
import org.tmf.dsmapi.agreement.model.Agreement;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  An async function that will be called for any type of changes to an Agreement resource
  @param bean
**/

@Stateless
@Asynchronous
public class AgreementEventPublisher implements EventPublisher<Agreement> {
    @EJB
    private AgreementEventFacade eventFacade;

    @EJB
    private HubFacade hubFacade;

    @EJB
    private AgreementRESTEventPuslisher restEventPuslisher;

    private static Logger logger = Logger.getLogger(AgreementEventPublisher.class.getName());

	// This is a blank implementation for the interface's method
    public void publish(Agreement bean) {

	}

    /**
     * This is overriden method to create and publish the given event
     * @param bean
    **/

    public void publish(AgreementEvent bean) {

		// Create an event
        try {
			eventFacade.create(bean);
        } catch(BadUsageException ex) {
            logger.log(Level.SEVERE, "Bad Usage", ex);
        }

        // Find all subcribers are notify them of the event
        List<Hub> hubList = hubFacade.findAll();

        if(hubList != null && !hubList.isEmpty()) {
            for (Hub hub :hubList) {
                restEventPuslisher.publish(hub, bean);
            }
        }
    }

    /**
     * This function should be called for create, update, delete (All events defined in AgreementEventNum;
     * function will take bean , date and eventType;
     * @param bean  // type of object which need to be created
     * @param date // date of notification
     * @param eventType // type of the event
     */

    public void generateEventNotification(Agreement bean, Date date, AgreementEventEnum eventType) {
        //Create new event;
        AgreementEvent event = new AgreementEvent();

        //Set event date
        event.setEventTime(date);
        //specify the type of event as create
        event.setEventType(eventType);
        //set the event body
        event.setResource(bean);

        //publish the event
        publish(event);
    }
}
