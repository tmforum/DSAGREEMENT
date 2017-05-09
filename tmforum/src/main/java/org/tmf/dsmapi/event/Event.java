package org.tmf.dsmapi.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tmf.dsmapi.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.model.Agreement;


@SuppressWarnings("All")
@Entity
@Table(name="EVENT_AGREEMENT")

public class Event<T>  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Enumerated(value = EnumType.STRING)
    private AgreementEventEnum eventType;

    //@Transient
    @JsonProperty("eventBody")
    @Column(name = "eventBody", length = 10000)
    private T resource; //check for object

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public AgreementEventEnum getEventType() {
        return eventType;
    }

    public void setEventType(AgreementEventEnum eventType) {
        this.eventType = eventType;
    }


    public T getResource() {
        return resource;
    }

    public void setResource(T resource) {
        this.resource = resource;
    }


//    class EventBody {
//        private Agreement entity;
//        public Agreement getEntity() {
//            return entity;
//        }
//        public EventBody(Agreement entity) {
//            this.entity = entity;
//        }
//    }
//
//
//    public EventBody getEvent() {
//        return new EventBody(getResource() );
//    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", eventTime=" + eventTime +
                ", eventType=" + eventType +
                ", resource=" + resource +
                '}';
    }
}
