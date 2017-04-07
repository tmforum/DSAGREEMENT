package org.tmf.dsmapi.agreement.event;

import org.tmf.dsmapi.agreement.model.Agreement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENT_AGREEMENT")
public class AgreementEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String eventId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Enumerated(value = EnumType.STRING)
    private AgreementEventEnum eventType;

    private Agreement resource;

    private AgreementEventBody event;

    public AgreementEventBody getEvent() {
        return new AgreementEventBody(getResource());
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public Agreement getResource() {
        return resource;
    }

    public void setResource(Agreement resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "AgreementEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventTime=" + eventTime +
                ", eventType=" + eventType +
                ", resource=" + resource +
                ", event=" + event +
                '}';
    }
}
