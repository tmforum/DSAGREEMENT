package org.tmf.dsmapi.agreement.model;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "AgreementSpecificationRelationship")
@Table(name = "AGREEMENT_SPEC_RELATIONSHIP")
@Inheritance(strategy = InheritanceType.JOINED)
public class AgreementSpecificationRelationship {

    protected String id;
    protected String href;
    protected String type;
    protected TimePeriod validFor;

    public AgreementSpecificationRelationship() {
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
