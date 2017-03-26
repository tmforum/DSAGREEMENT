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

/**
 * Created by atinsingh on 3/20/17.
 */
@Embeddable
public class ProductOfferingRef {

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

    @Override
    public String toString() {
        return "ProductOfferingRef{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", bundledProductOffering=" + bundledProductOffering +
                '}';
    }
	*/
}
