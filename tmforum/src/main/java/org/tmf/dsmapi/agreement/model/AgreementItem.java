package org.tmf.dsmapi.agreement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AGREEMENT_ITEM")
public class AgreementItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "AGREEMENT_ITEM_ID")
	protected String agrItemId;

	//@ElementCollection
	//@CollectionTable(name="PRODUCT_OFFERING")
    @OneToMany(targetEntity = ProductOfferingRef.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "PO_AGREEMENT_ID")
    protected List<ProductOfferingRef> productOffering;

	//@ElementCollection
	//@CollectionTable(name="T_OR_C")
    @OneToMany(targetEntity = AgreementTermOrCondition.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "TC_AGREEMENT_ID")
    protected List<AgreementTermOrCondition> termOrCondition;

    public List<ProductOfferingRef> getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(List<ProductOfferingRef> productOffering) {
        this.productOffering = productOffering;
    }

    public List<AgreementTermOrCondition> getTermOrCondition() {
        return termOrCondition;
    }

    public void setTermOrCondition(List<AgreementTermOrCondition> termOrConditions) {
        this.termOrCondition = termOrConditions;
    }

    @Override
    public String toString() {
        return "AgreementItem{" +
                "productOffering=" + productOffering +
                ", termOrConditions=" + termOrCondition +
                '}';
    }
}
