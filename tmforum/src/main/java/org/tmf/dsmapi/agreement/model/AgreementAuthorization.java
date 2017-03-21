package org.tmf.dsmapi.agreement.model;

import java.util.Date;

/**
 * Created by atinsingh on 3/20/17.
 */
public class AgreementAuthorization {

    protected Date date;
    protected String signatureRepresentation;
    protected String state;


    public AgreementAuthorization(Date date, String signatureRepresentation, String state) {
        this.date = date;
        this.signatureRepresentation = signatureRepresentation;
        this.state = state;
    }

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
