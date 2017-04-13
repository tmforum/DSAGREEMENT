package org.tmf.dsmapi.agreement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_AUTH")
public class AgreementAuthorization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AGREEMENT_AUTH_ID_PK")
	String agrAuthId;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;

    //Indication that represents whether the signature is a physical paper signature or a digital signature.
    protected String signatureRepresentation;

    //Current status of the authorization, for example in process, approved, rejected.
    protected String state;

	/*
    public AgreementAuthorization(Date date, String signatureRepresentation, String state) {
        this.date = date;
        this.signatureRepresentation = signatureRepresentation;
        this.state = state;
    }
	*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSignatureRepresentation() {
        return signatureRepresentation;
    }

    public void setSignatureRepresentation(String signatureRepresentation) {
        this.signatureRepresentation = signatureRepresentation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AgreementAuthorization{" +
                "date=" + date +
                ", signatureRepresentation='" + signatureRepresentation + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
