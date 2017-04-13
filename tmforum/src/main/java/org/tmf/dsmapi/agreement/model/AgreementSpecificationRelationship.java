package org.tmf.dsmapi.agreement.model;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_SPEC_RELATIONSHIP")
public class AgreementSpecificationRelationship {

    @Id
    @Column(name = "AGREEMENT_SPEC_RELATIONSHIP_PK")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String agreementSpecRelnPk;


    //Unique identifier of the related agreement specification.
    @Column(name = "AGREEMENT_SPEC_REL_AGREEMENT_SPEC_ID")
    protected String id;

    //Reference of an agreement specification
    protected String href;

    //Type of relationship such as, substitution or dependency
    protected String type;

	@Embedded
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
