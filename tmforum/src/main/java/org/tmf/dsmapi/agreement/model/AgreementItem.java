package org.tmf.dsmapi.agreement.model;

import java.util.List;

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
@Table(name="AGREEMENT_ITEM")
public class AgreementItem {

	/* This field will be added automatically by JPA DDL for join
	@Column(name="FK_AGREEMENT_ID")
	protected String id;
	*/

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "AGREEMENT_ITEM_ID")
	protected String agrItemId;

	@ElementCollection
	@CollectionTable(name="PRODUCT_OFFERING")
    protected List<ProductOfferingRef> productOffering;

	@ElementCollection
	@CollectionTable(name="T_OR_C")
    protected List<AgreementTermOrCondition> termOrConditions;


	/*
    public AgreementItem(List<ProductOfferingRef> productOffering, List<AgreementTermOrCondition> termOrConditions) {
        this.productOffering = productOffering;
        this.termOrConditions = termOrConditions;
    }
	*/

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
