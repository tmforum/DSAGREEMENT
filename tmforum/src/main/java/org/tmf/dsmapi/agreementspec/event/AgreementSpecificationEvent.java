package org.tmf.dsmapi.agreementspec.event;

import org.tmf.dsmapi.agreement.event.AgreementEventEnum;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by atinsingh on 4/19/17.
 */

@Entity
@Table(name = "EVENT_AGREEMENT_SPEC")
public class AgreementSpecificationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Enumerated(value = EnumType.STRING)
    private AgreementEventEnum eventType;

    @Transient
    private AgreementSpecification resource; //check for object

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


    public AgreementSpecification getResource() {
        return resource;
    }

    public void setResource(AgreementSpecification resource) {
        this.resource = resource;
    }


    class EventBody {
        private AgreementSpecification entity;
        public AgreementSpecification getEntity() {
            return entity;
        }
        public EventBody(AgreementSpecification entity) {
            this.entity = entity;
        }
    }


    public EventBody getEvent() {
        return new EventBody(getResource() );
    }

    @Override
    public String toString() {
        return "AgreementSpecificationEvent{" +
                "id='" + id + '\'' +
                ", eventTime=" + eventTime +
                ", eventType=" + eventType +
                ", resource=" + resource +
                '}';
    }
}
