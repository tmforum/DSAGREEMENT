package org.tmf.dsmapi.agreement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateDeSerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_SPECS")
@XmlRootElement
public class AgreementSpecification  {

    private static final long serialVersionUID = 11L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_SPECS_ID_PK")
    protected String id;

    protected String href;

    protected String description;


    protected Boolean isBundle;


    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    protected Date lastUpdate;

    //Indicates the current lifecycle status
    @Enumerated(EnumType.STRING)
    protected AgreementStatusEnum lifecycleStatus;
    //Name of the agreement specification
    protected String name;

	  @Embedded
    @JsonDeserialize(as = TimePeriod.class)
    protected TimePeriod validFor;
    //Agreement specification version
    protected String version;

    @ManyToOne(targetEntity = CategoryRef.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "CAT_REF_AGREEMENT_SPEC_ID_FK")
    //The category resource is used to group product offerings, service and resource candidates in logical containers
    protected CategoryRef serviceCategory;

    @OneToMany(targetEntity = AgreementSpecCharacteristic.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "SPEC_CHAR_AGREEMENT_SPEC_ID_FK")
    //A list of agreement spec characteristics
    protected List<AgreementSpecCharacteristic> specCharacteristic;

    @OneToMany(targetEntity = AgreementAttachment.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ATTACHMENT_AGREEMENT_SPEC_ID_FK")
    //A list of agreement attachments
    protected List<AgreementAttachment> attachment;

    @OneToMany(targetEntity = AgreementSpecificationRelationship.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "AGREEMENT_SPEC_REL_AGREEMENT_SPEC_ID_FK")
    //A list of agreement specification relationships
    protected List<AgreementSpecificationRelationship> specificationRelationship;

    //A list of related party references (RelatedPartyRef [*]). A related party defines party or party role linked to a specific entity.
    @OneToMany(targetEntity = RelatedPartyRef.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "AGREEMENT_SPEC_RELATED_PARTY_REF")
    protected List<RelatedPartyRef> relatedParty;



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

    public Boolean getIsBundle() {
        return isBundle;
    }

    /**
     * Set the value of property isBundle
     *
     * @param bundle
     *  allowed object is
     *  {@link Boolean}
     */

    public void setIsBundle(Boolean bundle) {
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
    //@JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    public void setLastUpdate(Date lastUpdate) {
       this.lastUpdate = lastUpdate;
    }


    /**
     * Returns the lifeCycleStatus property of the object
     * @return
     * allowed object is
     * {@link String}
     */
    public AgreementStatusEnum getLifecycleStatus() {
        return lifecycleStatus;
    }

    /**
     * Sets the value of propery lifecycleStatus
     * @param lifecycleSattus
     * allowed object is
     */
    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = AgreementStatusEnum.fromValue(lifecycleStatus);
    }

    /**
     * Returns the name property of the object
     * @return
     * allowed object is
     * {@link String}
     */


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
    public List<AgreementSpecCharacteristic> getSpecCharacteristic() {
        if(specCharacteristic==null){
            specCharacteristic = new ArrayList<AgreementSpecCharacteristic>();
        }
        return specCharacteristic;
    }

    /**
     *
     *
     * @param specCharacteristics
     */
    public void setSpecCharacteristic(List<AgreementSpecCharacteristic> specCharacteristic) {
        this.specCharacteristic = specCharacteristic;
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
         if(this.attachment==null) {
             this.attachment = attachment;
         }else {
             this.attachment.addAll(attachment);
         }
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


    public List<RelatedPartyRef> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedPartyRef> relatedParty) {
        this.relatedParty = relatedParty;
    }

    @Override
    public String toString() {
        return "AgreementSpecification{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", description='" + description + '\'' +
                ", isBundle=" + isBundle +
                ", lastUpdate=" + lastUpdate +
                ", lifecycleStatus='" + lifecycleStatus.toString() + '\'' +
                ", name='" + name + '\'' +
                ", validFor=" + validFor +
                ", version='" + version + '\'' +
                ", serviceCategory=" + serviceCategory +
                ", specCharacteristics=" + specCharacteristic +
                ", attachment=" + attachment +
                ", specificationRelationship=" + specificationRelationship +
                '}';
    }
}
