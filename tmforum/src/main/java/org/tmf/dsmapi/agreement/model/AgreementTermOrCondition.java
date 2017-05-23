package org.tmf.dsmapi.agreement.model;

import javax.persistence.*;

@Embeddable
//@Entity
//@Table(name = "T_OR_C")
public class AgreementTermOrCondition {

    //@Id
    //@GeneratedValue
    //@Column(name = "TC_ID")
    protected String id;

    protected String description;

    protected TimePeriod validFor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    @Override
    public String toString() {
        return "AgreementTermOrCondition{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
