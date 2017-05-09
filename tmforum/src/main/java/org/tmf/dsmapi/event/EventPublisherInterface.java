package org.tmf.dsmapi.event;

import org.tmf.dsmapi.event.AgreementEventEnum;

import javax.ejb.Local;
import java.util.Date;

/**
 * Created by Dharmendra Sengar on 4/19/17.
 */

@Local
public interface EventPublisherInterface<T> {

    //publish an event of type T

    /**
     *
     * @param bean
     */

    void publish(T bean);

    /**
     *
     * @param bean  // type of object which need to be created
     * @param date // date of notification
     * @param eventType // type of the event
     */
    void generateEventNotification(T bean, Date date, AgreementEventEnum eventType);
}
