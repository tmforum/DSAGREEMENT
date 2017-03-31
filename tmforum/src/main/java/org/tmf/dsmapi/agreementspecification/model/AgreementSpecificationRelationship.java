package org.tmf.dsmapi.agreementmanagement.agreementspecification.model;

import org.tmf.dsmapi.agreementmanagement.agreement.model.TimePeriod;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Created by atinsingh on 3/21/17.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "AgreementSpecificationRelationship")
@Table(name = "AGREEMENT_SPEC_RELATIONSHIP")
@Inheritance(strategy = InheritanceType.JOINED)
public class AgreementSpecificationRelationship implements Serializable {

    protected String id;
    protected String href;
    protected String type;
    protected TimePeriod validFor;


    public AgreementSpecificationRelationship() {
    }

    public AgreementSpecificationRelationship(String id, String href, String type, TimePeriod validFor) {
        this.id = id;
        this.href = href;
        this.type = type;
        this.validFor = validFor;
    }

    /**
     * Return the ID of the AgreementSpecificationRelationship
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *
     * allowed object is
     * {@link String}
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Return the HREF of the AgreementSpecificationRelationship
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "HREF", length = 255)
    public String getHref() {
        return href;
    }

    /**
     * Sets HREF for the object
     * @param href
     *
     * allowed object is
     * {@link String}
     */
    public void setHref(String href) {
        this.href = href;
    }


    /**
     * Return the Type of the AgreementSpecificationRelationship
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "TYPE",length = 255)
    public String getType() {
        return type;
    }

    /**
     * Set the type for the object
     * @param type
     *
     * allowed object is
     * {@link String}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Return the timeperiod of the object
     * @return
     *
     * allowed object is
     *
     * {@link TimePeriod}
     */
    @Embedded
    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     * Set time period for the object
     * @param validFor
     *
     * allowed object is
     * {@link String}
     */
    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    @Override
    public String toString() {
        return "AgreementSpecificationRelationship{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", type='" + type + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
