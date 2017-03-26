package org.tmf.dsmapi.agreement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="AGREEMENT")
public class Agreement implements Serializable {
	@Transient
    private static final long serialVersionUID = 11L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AGREEMENT_ID")
    protected String id;

	@Embedded
    protected TimePeriod agreementPeriod;

	@Embedded
    protected TimePeriod completionPeriod;

    protected String description;

    protected int documentNumber;

    protected String href;

    protected Date intialDate;

    protected String name;

    protected String statementOfIntent;

    protected String status;

    protected String type;

    protected String version;

	@Embedded
    protected AgreementSpecificationRef agreementSpecification;

	@OneToMany(cascade=CascadeType.ALL)
	@ElementCollection
	@CollectionTable(name="AGREEMENT_ITEM", joinColumns=@JoinColumn(name="AGREEMENT_ID"))
    protected List<AgreementItem> agreementItem;

	@ElementCollection
	@CollectionTable(name="PARTY_ROLE_REF")
	@Column(name="ENGAGED_PARTY_ROLE")
    protected List<PartyRoleRef> engagedPartyRole;

	@ElementCollection
	@CollectionTable(name="AGREEMENT_AUTH")
	@Column(name="AGREEMENT_AUTH")
    protected List<AgreementAuthorization> agreementAuthorization;

	@ElementCollection
	@CollectionTable(name="CHARACTERISTIC")
    protected List<Characteristic> characteristic;

	@ElementCollection
	@CollectionTable(name="AGREEMENT_REF")
	@Column(name="ASSOC_AGREEMENT")
    protected List<AgreementRef> associatedAgreement;

    public TimePeriod getAgreementPeriod() {
        return agreementPeriod;
    }

    public void setAgreementPeriod(TimePeriod agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public TimePeriod getCompletionPeriod() {
        return completionPeriod;
    }

    public void setCompletionPeriod(TimePeriod completionPeriod) {
        this.completionPeriod = completionPeriod;
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

    public Date getIntialDate() {
        return intialDate;
    }

    public void setIntialDate(Date intialDate) {
        this.intialDate = intialDate;
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
                ", completionPeriod=" + completionPeriod +
                ", description='" + description + '\'' +
                ", documentNumber=" + documentNumber +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", intialDate=" + intialDate +
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
