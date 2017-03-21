package org.tmf.dsmapi.agreement.model;

import java.util.List;

/**
 * Created by atinsingh on 3/20/17.
 */
public class AgreementItem {

    protected List<ProductOfferingRef> productOffering;

    protected List<AgreementTermOrCondition>termOrConditions;


    public AgreementItem(List<ProductOfferingRef> productOffering, List<AgreementTermOrCondition> termOrConditions) {
        this.productOffering = productOffering;
        this.termOrConditions = termOrConditions;
    }

    public List<ProductOfferingRef> getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(List<ProductOfferingRef> productOffering) {
        this.productOffering = productOffering;
    }

    public List<AgreementTermOrCondition> getTermOrConditions() {
        return termOrConditions;
    }

    public void setTermOrConditions(List<AgreementTermOrCondition> termOrConditions) {
        this.termOrConditions = termOrConditions;
    }

    @Override
    public String toString() {
        return "AgreementItem{" +
                "productOffering=" + productOffering +
                ", termOrConditions=" + termOrConditions +
                '}';
    }
}
