package org.tmf.dsmapi.agreement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/* This may end up being a ManyToMany relationship
   Needs further refinement
*/

@Entity
@Table(name = "CHARACTERISTIC")
public class Characteristic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHARACTERISTIC_ID")
	protected String charId;

    protected String name;
    protected String value;

	//@ManyToMany(mappedBy = "characteristic")
	//@ManyToMany
	//protected List<Agreement> agreementChar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
