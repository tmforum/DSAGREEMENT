package org.tmf.dsmapi.agreement.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AGREEMENT_AUTH")
public class AgreementAuthorization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AGREEMENT_AUTH_ID")
	String agrAuthId;

    protected Date date;

    protected String signatureRepresentation;

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
