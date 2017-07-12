package org.tmf.dsmapi.agreement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateDeSerializer;

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT")
public class Agreement implements Serializable {

    @Transient
    private static final long serialVersionUID = 11L;

    // primary identification key for Ageement
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_ID")
    protected String id;

    //Agreement validfor - StartDateTime and EndDateTime
    @Embedded
    protected TimePeriod agreementPeriod;

    //Agreement completion datetime
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    protected Date completionDate;

    //Decription of Agreement
    protected String description;

    //Document Number for an Agreement
    protected int documentNumber;

    protected String href;

    //Initial date of an agreemnt
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    protected Date initialDate;

    //Name of the agreement
    protected String name;

    //Statement fo Intent for an Agreement
    protected String statementOfIntent;


    //Life cycle status of agreement - of Initialized , InProcess, Completed, Rejected
    @Enumerated(EnumType.STRING)
    protected AgreementStatusEnum status;

    //Type of the agreement
    protected String type;

    //Version of the agreement
    protected String version;

    //Associated agreement Specification with Agreement

    @OneToOne(targetEntity = AgreementSpecificationRef.class, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_AGREEMENT_SPEC_REF_AGREEMENT")
    protected AgreementSpecificationRef agreementSpecification;

    // The below OneToMany and JoinColumn statements will create
    // the named FK in the corresponding referred table

    @OneToMany(targetEntity = AgreementItem.class, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_AGREEMENT_ITEM_AGREEMENT")
    protected List<AgreementItem> agreementItem;

    @OneToMany(targetEntity = PartyRoleRef.class, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_PARTY_ROLE_AGREEMENT")
    protected List<PartyRoleRef> engagedPartyRole;

    @OneToMany(targetEntity = AgreementAuthorization.class, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_AGREEMENT_AUTH_AGREEMENT")
    protected List<AgreementAuthorization> agreementAuthorization;

    //@ManyToMany(cascade = CascadeType.ALL, mappedBy = "agreementChar")

    @OneToMany(targetEntity = Characteristic.class, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_CHARACTERISTIC_AGREEMENT")
    protected List<Characteristic> characteristic;

    @OneToMany(targetEntity = AgreementRef.class , orphanRemoval = true, cascade = { CascadeType.ALL } )
    @JoinColumn(name = "FK_AGREEMENT_REF_AGREEMENT")
    protected List<AgreementRef> associatedAgreement;

    /**
     * Get the Agreement Priod
     * @return
     */
    public TimePeriod getAgreementPeriod() {
        return agreementPeriod;
    }

    /**
     * Setter for Agreement Period
     * @param agreementPeriod
     */
    public void setAgreementPeriod(TimePeriod agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    /**
     * Getter for completionDate
     * @return completionDate
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * Setter for completionDate
     * @param completionDate
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for documentNumber
     * @return
     */
    public int getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Setter for documentNumber
     * @param documentNumber
     */
    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Getter for href
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * Setter href
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Getter for id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for initialDate
     * @return
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * Setter for initialDate
     * @param intialDate
     */
    public void setInitialDate(Date intialDate) {
        this.initialDate = intialDate;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for statementOfIntent
     * @return statementOfIntent
     */
    public String getStatementOfIntent() {
        return statementOfIntent;
    }

    public void setStatementOfIntent(String statementOfIntent) {
        this.statementOfIntent = statementOfIntent;
    }

    /**
     * Getter for status
     * @return status
     */
    public AgreementStatusEnum getStatus() {
        return status;
    }

    /**
     * Setter for status
     * @param status
     */
    public void setStatus(String status) {
        this.status = AgreementStatusEnum.fromValue(status);
        ;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for version
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter for version
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Return the list of associated agreementSpecification as reference
     * @return
     * {@link AgreementSpecificationRef}
     */
    public AgreementSpecificationRef getAgreementSpecification() {
        return agreementSpecification;
    }

    /**
     * Add the associated AgreementSpecificationRef
     * @param agreementSpecification
     *
     * {@link AgreementSpecificationRef}
     */
    public void setAgreementSpecification(AgreementSpecificationRef agreementSpecification) {
        this.agreementSpecification = agreementSpecification;
    }

    /**
     * Get the associated Agreement Items for agreement
     * @return
     * {@link List<AgreementItem>}
     */
    public List<AgreementItem> getAgreementItem() {
        return agreementItem;
    }

    /**
     * Add agreement items for agreement
     * @param agreementItem
     * {@link List<AgreementItem>}
     */
    public void setAgreementItem(List<AgreementItem> agreementItem) {
        this.agreementItem = agreementItem;
    }

    /**
     * Gets associated parties with agreement
     * @return
     *
     *{@link List<PartyRoleRef>}
     */
    public List<PartyRoleRef> getEngagedPartyRole() {
        return engagedPartyRole;
    }

    /**
     * Set associated parties
     * @param engagedPartyRole
     */
    public void setEngagedPartyRole(List<PartyRoleRef> engagedPartyRole) {
        this.engagedPartyRole = engagedPartyRole;
    }

    /**
     * Return associated authorization for an Agreement
     * @return
     */
    public List<AgreementAuthorization> getAgreementAuthorization() {
        if(agreementAuthorization==null){
            agreementAuthorization = new ArrayList<AgreementAuthorization>();
        }
        return agreementAuthorization;
    }


    /**
     * Set the associated authorization for agreement
     * @param agreementAuthorization
     */
    public void setAgreementAuthorization(List<AgreementAuthorization> agreementAuthorization) {
        this.agreementAuthorization = agreementAuthorization;
    }

    /**
     * Gets the characteristic for an agreement
     * @return characteristic
     */
    public List<Characteristic> getCharacteristic() {
        return characteristic;
    }

    /**
     * Sets the characteristic
     * @param characteristic
     */
    public void setCharacteristic(List<Characteristic> characteristic) {
        this.characteristic = characteristic;
    }

    /**
     * Gets the associatedAgreement
     * @return associatedAgreement
     */
    public List<AgreementRef> getAssociatedAgreement() {
        return associatedAgreement;
    }

    /**
     * Sets the associatedAgreement
     * @param associatedAgreement
     */
    public void setAssociatedAgreement(List<AgreementRef> associatedAgreement) {
        this.associatedAgreement = associatedAgreement;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "agreementPeriod=" + agreementPeriod +
                ", completionDate=" + completionDate +
                ", description='" + description + '\'' +
                ", documentNumber=" + documentNumber +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", intialDate=" + initialDate +
                ", name='" + name + '\'' +
                ", statementOfIntent='" + statementOfIntent + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", agreementSpecification=" + agreementSpecification +
                ", agreementItem=" + agreementItem +
                ", engagedPartyRole=" + engagedPartyRole +
                ", agreementAuthorization=" + agreementAuthorization +
                ", characteristic=" + characteristic +
                ", associatedAgreement=" + associatedAgreement +
                '}';
    }
}
