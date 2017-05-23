package org.tmf.dsmapi.agreement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

// No PK for this table. To be added later based on performance

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_SPEC_REF")
@XmlRootElement
public class AgreementSpecificationRef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_SPEC_REF_PK")
    protected String agreementSpecRefPk;

    @Column(name = "AGREEMENT_SPEC_DESC")
    //detail what the agreement specification is about.
    protected String description;

    @Column
    protected String href;

    //Unique identifier of the agreement specification.
    @Column(name = "AGREEMENT_SPEC_ID_FK")
    protected String id;

    @Column
    //Name of the agreement specification
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
