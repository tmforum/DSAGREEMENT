package org.tmf.dsmapi.agreement.model;

import javax.persistence.*;

@Embeddable
//@Entity
//@Table(name = "PRODUCT_OFFERING")
public class ProductOfferingRef {

    //@Id
    //@GeneratedValue
    //@Column(name = "PO_ID")
    protected String id;
    protected String href;
    protected String name;

	/* Pending CR from Pierre. Field to be removed
    @ElementCollection
	@CollectionTable(name="BUNDLED_PRODUCT_OFFERING")
    protected List<ProductOfferingRef> bundledProductOffering;
	*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /* Pending CR from Pierre. Field to be removed
    public List<ProductOfferingRef> getBundledProductOffering() {
        return bundledProductOffering;
    }

    public void setBundledProductOffering(List<ProductOfferingRef> bundledProductOffering) {
        this.bundledProductOffering = bundledProductOffering;
    }
    */
    @Override
    public String toString() {
        return "ProductOfferingRef{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
