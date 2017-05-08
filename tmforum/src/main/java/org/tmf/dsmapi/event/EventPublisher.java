package org.tmf.dsmapi.event;

import org.tmf.dsmapi.agreement.event.AgreementEventEnum;
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
 * Created by atinsingh on 5/7/17.
 */
@Stateless
@Asynchronous
public class EventPublisher<T> {




    @EJB
    private EventFacade<Event<T>> eventFacade ;
    @EJB
    private HubFacade hubFacade;

    @EJB
    private RestEventPublisher<Event<T>> restEventPublisher;

    private static Logger logger = Logger.getLogger(EventPublisher.class.getName());


    /**
     * This is overriden method to create and publish the given event
     * @param bean
     **/

    public void publish(Event<T> bean) {

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
                restEventPublisher.publish(hub,bean.getId(),bean);
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

    public void  generateEventNotification (T bean, Date date, AgreementEventEnum eventType) {
        //Create new event;
        Event<T> event = new Event<T>();

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
