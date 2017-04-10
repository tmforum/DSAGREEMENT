package org.tmf.dsmapi.agreement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGREEMENT_REF")
public class AgreementRef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AGREEMENT_REF_ID")
	protected String agrRefId;

	@Column(name = "AGREEMENT_ID")
    protected String id;

    protected String name;

    protected String href;

	/*
    public AgreementRef(String id, String name, String href) {
        this.id = id;
        this.name = name;
        this.href = href;
    }
	*/

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
