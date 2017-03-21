package org.tmf.dsmapi.agreement.model;

/**
 * Created by atinsingh on 3/20/17.
 */
public class AgreementRef {

    protected String id;
    protected String name;
    protected String href;

    public AgreementRef(String id, String name, String href) {
        this.id = id;
        this.name = name;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
