package org.tmf.dsmapi.agreement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "PARTY_ROLE_REF")
public class PartyRoleRef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PARTY_ROLE_REF_PK")
    //Unique identifier of the product
    protected String id;

    //Reference of the product
    protected String href;

    //The name of the referred party role.
    protected String name;

    //The identifier of the engaged party that is linked to the PartyRole object.
    protected String partyID;  // Should be replaced with PartyRole object.

    protected String partyName;

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

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    @Override
    public String toString() {
        return "PartyRoleRef{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", partyID='" + partyID + '\'' +
                ", partyName='" + partyName + '\'' +
                '}';
    }
}
