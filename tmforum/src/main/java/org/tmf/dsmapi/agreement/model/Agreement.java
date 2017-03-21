package org.tmf.dsmapi.agreement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by atinsingh on 3/20/17.
 */
public class Agreement implements Serializable {

    private static final long serialVersionUID = 11L;

    protected TimePeriod agreementPeriod;

    protected TimePeriod completionPeriod;

    protected String description;

    protected int documentNumber;

    protected String href;

    protected String id;

    protected Date intialDate;

    protected String name;

    protected String statementOfIntent;

    protected String status;

    protected String type;

    protected String version;

    protected AgreementSpecificationRef agreementSpecification;

    protected List<AgreementItem> agreementItem;

    protected List<PartyRoleRef> engagedPartyRole;

    protected List<AgreementAuthorization> agreementAuthorization;

    protected List<Characteristic> characteristic;

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
