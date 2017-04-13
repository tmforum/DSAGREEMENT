package org.tmf.dsmapi.agreement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "AGREEMENT")
public class Agreement implements Serializable {
	@Transient
    private static final long serialVersionUID = 11L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AGREEMENT_ID")
    protected String id;

	@Embedded
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    protected TimePeriod agreementPeriod;

	/*
	@Embedded
    protected TimePeriod completionPeriod;
	*/

	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected Date completionDate;

    protected String description;

    protected int documentNumber;

    protected String href;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected Date initialDate;

    protected String name;

    protected String statementOfIntent;

    protected String status;

    protected String type;

    protected String version;

	@OneToOne(targetEntity = AgreementSpecificationRef.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AGREEMENT_SPEC_REF_AGREEMENT")
    protected AgreementSpecificationRef agreementSpecification;

	// The below OneToMany and JoinColumn statements will create 
	// the named FK in the corresponding referred table

	@OneToMany(targetEntity = AgreementItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AGREEMENT_ITEM_AGREEMENT")
    protected List<AgreementItem> agreementItem;

	@OneToMany(targetEntity = PartyRoleRef.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_PARTY_ROLE_AGREEMENT")
    protected List<PartyRoleRef> engagedPartyRole;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AGREEMENT_AUTH_AGREEMENT")
    protected List<AgreementAuthorization> agreementAuthorization;

	//@ManyToMany(cascade = CascadeType.ALL, mappedBy = "agreementChar")

    @OneToMany(targetEntity = Characteristic.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_CHARACTERISTIC_AGREEMENT")
    protected List<Characteristic> characteristic;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AGREEMENT_REF_AGREEMENT")
    protected List<AgreementRef> associatedAgreement;

    public TimePeriod getAgreementPeriod() {
        return agreementPeriod;
    }

    public void setAgreementPeriod(TimePeriod agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date intialDate) {
        this.initialDate = intialDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatementOfIntent() {
        return statementOfIntent;
    }

    public void setStatementOfIntent(String statementOfIntent) {
        this.statementOfIntent = statementOfIntent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AgreementSpecificationRef getAgreementSpecification() {
        return agreementSpecification;
    }

    public void setAgreementSpecification(AgreementSpecificationRef agreementSpecification) {
        this.agreementSpecification = agreementSpecification;
    }

    public List<AgreementItem> getAgreementItem() {
        return agreementItem;
    }

    public void setAgreementItem(List<AgreementItem> agreementItem) {
        this.agreementItem = agreementItem;
    }

    public List<PartyRoleRef> getEngagedPartyRole() {
        return engagedPartyRole;
    }

    public void setEngagedPartyRole(List<PartyRoleRef> engagedPartyRole) {
        this.engagedPartyRole = engagedPartyRole;
    }

    public List<AgreementAuthorization> getAgreementAuthorization() {
        return agreementAuthorization;
    }

    public void setAgreementAuthorization(List<AgreementAuthorization> agreementAuthorization) {
        this.agreementAuthorization = agreementAuthorization;
    }

    public List<Characteristic> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(List<Characteristic> characteristic) {
        this.characteristic = characteristic;
    }

    public List<AgreementRef> getAssociatedAgreement() {
        return associatedAgreement;
    }

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
