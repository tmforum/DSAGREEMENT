package org.tmf.dsmapi.agreementspec.event;

import org.apache.commons.logging.Log;
import org.tmf.dsmapi.agreement.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.event.EventPublisher;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
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
 * Created by atinsingh on 4/19/17.
 */
@Stateless
@Asynchronous
public class AgreementSpecificationEventPublisher  implements EventPublisher<AgreementSpecification> {
    /**
     *  Function will be called whenever a new AgreementSpecification is created in the system.
     * @param bean
     */

    @EJB
    private AgreementSpecificationEventFacade eventFacade;

    @EJB
    private HubFacade hubFacade;

    @EJB
    private AgreementSpecificationRESTEventPuslisher restEventPuslisher;

    private static Logger logger = Logger.getLogger(AgreementSpecificationEventPublisher.class.getName());

    public void publish(AgreementSpecification bean) {

    }

    /**
     * This is overriden method to create and publish the event
     * @param bean
     */


    public void publish(AgreementSpecificationEvent bean) {
        //create an event
        try {
                eventFacade.create(bean);
        }catch (BadUsageException ex){
            logger.log(Level.SEVERE, "Bad Usage", ex);
        }
        //now find all the subcribers are push to them

        List<Hub> hubList = hubFacade.findAll();
        if(hubList!=null&&!hubList.isEmpty()){
            for (Hub hub :hubList){
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

    public void generateEventNotification(AgreementSpecification bean, Date date, AgreementEventEnum eventType) {
        //Create new event;
        AgreementSpecificationEvent event = new AgreementSpecificationEvent();
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
