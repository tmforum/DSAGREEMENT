package org.tmf.dsmapi.agreement.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "AgreementSpecs")
@Table(name = "AGREEMENT_SPECIFICATION")
@Inheritance(strategy = InheritanceType.JOINED)
public class AgreementSpecification  {

    private static final long serialVersionUID = 11L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_SPEC_ID")
    protected String id;

    protected String href;

    protected String description;

    protected Boolean isBundle;

    @Basic
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdate;

    @Basic
    @Column(name = "LIFE_CYCLE_STATUS" , length = 255)
    protected AgreementStatusEnum lifeCycleStatus;

    protected String name;

	@Embedded
    protected TimePeriod validFor;

    protected String version;

    @ManyToOne(targetEntity = CategoryRef.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "CATEGORY_REF_AGREEMENT_SPEC_ID")
    protected CategoryRef serviceCategory;

    @OneToMany(targetEntity = AgreementSpecCharacteristic.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "SPEC_CHARACTERISTIC_AGREEMENT_SPEC_ID")
    protected List<AgreementSpecCharacteristic> specCharacteristics;

    @OneToMany(targetEntity = AgreementAttachment.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "ATTACHMENT_AGREEMENT_SPEC_ID")
    protected List<AgreementAttachment> attachment;

    @OneToMany(targetEntity = AgreementSpecificationRelationship.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "AGREEMENT_SPEC_REL_AGREEMENT_SPEC_ID")
    protected List<AgreementSpecificationRelationship> specificationRelationship;

    @Transient
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Return the ID property of the object
     * @return
     * allowed object is
     * {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * Sets ID prorty of the object
     * @param id
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the HREF field of the object
     * @return
     *
     * allowed object is
     *
     * {@link String}
     */

    public String getHref() {
        return href;
    }

    /**
     * Sets HREF property of the object
     * @param href
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Returns the drescription property of the object
     * @return
     *
     * allowed object is
     *
     * {@link String}
     */

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description property of the object
     * @param description
     *
     * allowed object is
     * {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Return the iBundle propery of AgreementSepecification Object
     * @return
     *      {@link Boolean}
     */

    public Boolean getBundle() {
        return isBundle;
    }

    /**
     * Set the value of property isBundle
     *
     * @param bundle
     *  allowed object is
     *  {@link Boolean}
     */

    public void setBundle(Boolean bundle) {
        isBundle = bundle;
    }

    /**
     * Return the lastupdate date propery of the object
     *
     * @return
     * allowed object is
     * {@link String}
     *
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the lastupdate propery of the object
     *
     * @param lastUpdate
     * allowed object is
     * {@link String}
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Returns the lifeCycleStatus property of the object
     * @return
     * allowed object is
     * {@link String}
     */
    public AgreementStatusEnum getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    /**
     * Sets the value of propery lifecycleStatus
     * @param lifecycleSattus
     * allowed object is
     */
    public void setLifeCycleStatus(AgreementStatusEnum lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    /**
     * Returns the name property of the object
     * @return
     * allowed object is
     * {@link String}
     */

    @Basic
    @Column(name = "NAME", length = 255)
    public String getName() {
        return name;
    }

    /**
     * Sets the name property of the object
     * @param name
     * allowed object is
     * {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the timePeriod object as property
     * @return
     *
     * allowed object is TimePeriod
     * {@link TimePeriod}
     */
    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     * Set an embedded time period object
     * @param validFor
     *
     * allowed object is
     * {@link TimePeriod}
     */
    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }


    /**
     * Return the version property for the object
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "VERSION", length = 255)
    public String getVersion() {
        return version;
    }

    /**
     * Sets version property of the object
     * @param version
     *
     * allowed object is
     * {@link String}
     */
    public void setVersion(String version) {
        this.version = version;
    }


    /**
     * Returns the Service Category reference for the agreeementSpeficication.
     * @return
     *
     * Objects of the following type(s) are allowed in the list
     * {@link CategoryRef }
     */

    public CategoryRef getServiceCategory() {
        return serviceCategory;
    }

    /**
     * Sets Category REF
     *
     * @param serviceCategory
     *
     *
     */
    public void setServiceCategory(CategoryRef serviceCategory) {
        this.serviceCategory = serviceCategory;
    }



    /**
     * Gets all Agreement spefification charateristic
     *
     * @return
     *
     * allowed object is
     * {@link AgreementSpecCharacteristic}
     */
    public List<AgreementSpecCharacteristic> getSpecCharacteristics() {
        if(specCharacteristics==null){
            specCharacteristics = new ArrayList<AgreementSpecCharacteristic>();
        }
        return specCharacteristics;
    }

    /**
     *
     *
     * @param specCharacteristics
     */
    public void setSpecCharacteristics(List<AgreementSpecCharacteristic> specCharacteristics) {
        this.specCharacteristics = specCharacteristics;
    }

    /**
     * Gets all agreement attachment object associated with agreement spefication.
     * get/set will be allowed on there objects.
     *
     * @return
     * allowed object is
     * {@link AgreementAttachment}
     */
    public List<AgreementAttachment> getAttachment() {
        if(attachment==null){
            attachment = new ArrayList<AgreementAttachment>();
        }
        return attachment;
    }

    /**
     *
     *
     * @param attachment
     */
    public void setAttachment(List<AgreementAttachment> attachment) {
        this.attachment = attachment;
    }

    /**
     * Gets all AgreementSpecificationsRelationship associated with agreementspec
     * get/set will be permitted on the returned object
     *
     * @return
     *
     * allowed object is
     * {@link AgreementSpecificationRelationship}
     */

    public List<AgreementSpecificationRelationship> getSpecificationRelationship() {
        if(specificationRelationship==null){
            specificationRelationship = new ArrayList<AgreementSpecificationRelationship>();
        }
        return specificationRelationship;
    }

    /**
     *
     *
     * @param specificationRelationship
     *
     */
    public void setSpecificationRelationship(List<AgreementSpecificationRelationship> specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    @Override
    public String toString() {
        return "AgreementSpecification{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", description='" + description + '\'' +
                ", isBundle=" + isBundle +
                ", lastUpdate=" + lastUpdate +
                ", lifeCycleStatus='" + lifeCycleStatus.toString() + '\'' +
                ", name='" + name + '\'' +
                ", validFor=" + validFor +
                ", version='" + version + '\'' +
                ", serviceCategory=" + serviceCategory +
                ", specCharacteristics=" + specCharacteristics +
                ", attachment=" + attachment +
                ", specificationRelationship=" + specificationRelationship +
                '}';
    }
}
