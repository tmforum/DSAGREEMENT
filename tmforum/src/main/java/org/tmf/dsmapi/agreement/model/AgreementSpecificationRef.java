package org.tmf.dsmapi.agreement.model;

/**
 * Created by atinsingh on 3/20/17.
 */
public class AgreementSpecificationRef {

    protected String description;

    protected String href;

    protected String id;

    protected String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AgreementSpecificationRef{" +
                "description='" + description + '\'' +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
