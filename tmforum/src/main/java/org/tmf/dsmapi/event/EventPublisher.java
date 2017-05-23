package org.tmf.dsmapi.event;

import org.tmf.dsmapi.event.AgreementEventEnum;
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
 * This class provides a generic Event Publisher that can publish events for any resource
 * The generateEventNotification() function is called by the Facade layer, to generate events
 * and then notify listeners
 **/

@Stateless
@Asynchronous
public class EventPublisher<T> {
    private static Logger logger = Logger.getLogger(EventPublisher.class.getName());
    @EJB
    private EventFacade<Event<T>> eventFacade;
    @EJB
    private HubFacade hubFacade;
    @EJB
    private RestEventPublisher<Event<T>> restEventPublisher;

    /**
     * This is overriden method to create and publish the given event
     * This function is invoked by the generateEventNotification, to notify registered listeners
     **/

    public void publish(Event<T> bean) {

        // Create an event record
        try {
            eventFacade.create(bean);
        } catch (BadUsageException ex) {
            logger.log(Level.SEVERE, "Bad Usage", ex);
        }

        // Find all subcribers are notify them of the event
        List<Hub> hubList = hubFacade.findAll();

        if (hubList != null && !hubList.isEmpty()) {
            for (Hub hub : hubList) {
                restEventPublisher.publish(hub, bean.getId(), bean);
            }
        }
    }


    /**
     * This function should be called for create, update, delete (All events defined in AgreementEventNum;
     * function will take bean , date and eventType;
     *
     * @param bean      // type of object which need to be created
     * @param date      // date of notification
     * @param eventType // type of the event
     */

    public void generateEventNotification(T bean, Date date, AgreementEventEnum eventType) {
        // Create new event;
        Event<T> event = new Event<T>();

        // Set event attributes
        event.setEventTime(date);
        event.setEventType(eventType);
        event.setResource(bean);

        // Publish the event and notify listeners
        publish(event);
    }
}
