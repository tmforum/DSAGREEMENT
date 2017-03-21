package org.tmf.dsmapi.agreement.model;

import java.util.List;

/**
 * Created by atinsingh on 3/20/17.
 */
public class ProductOfferingRef {

    protected String id;
    protected String href;
    protected String name;
    protected List<ProductOfferingRef> bundledProductOffering;

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
}
