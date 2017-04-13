package org.tmf.dsmapi.agreement.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_SPEC_CHARACTERISTIC")
public class AgreementSpecCharacteristic implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_SPEC_CHAR_ID_PK")
    protected String id; 

    //indicates that the characteristic is configurable or not
    protected Boolean configurable;

    //A narrative that explains in detail what the characteristic is
    protected String description;

    //Name of the characteristic being specified
    protected String name;

	@Embedded
    protected TimePeriod validFor;

    //A kind of value that the characteristic can take on, such as numeric, text and so forth.
    protected String valueType;

    @OneToMany(targetEntity = AgreementSpecCharacteristicValue.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "SPEC_CHAR_VALUE_SPEC_CHAR_ID")
    //agreement spec characteristic values
    protected List<AgreementSpecCharacteristicValue> specCharacteristicValues;


    /**
     * Return if specs are configurable
     * @return
     *
     * allowed object is
     * {@link Boolean}
     */
    public Boolean getConfigurable() {
        return configurable;
    }

    /**
     * Set value of boolean propety configurable
     * @param configurable
     *
     * allowed object is
     * {@link Boolean}
     */
    public void setConfigurable(Boolean configurable) {
        this.configurable = configurable;
    }

    /**
     * Return description property of the object
     * @return
     * allowed object is
     * {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for Specs Characteristic
     * @param description
     *
     * allowed object is
     * {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the name of cascade = {
     CascadeType.ALL
     }
     * @return
     *
     * allowed object is
     * {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of Specs Characteristic
      * @param name
     *
     * allowed object is
     * {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the timeperiod object
     * @return
     *
     * allowed object is
     * {@link TimePeriod}
     */
    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     * Set the object TimePeriod
     * @param validFor
     *
     * allowed object is
     * {@link TimePeriod}
     */
    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * Return the value type of Specs Characteristic
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getValueType() {
        return valueType;
    }

    /**
     * Set the value type of Specs Characteristic
     * @param valueType
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * Returns the AgreeementSpecCharateristicValue for Specs Characteristic
     * get/set will be allowed
     * @return
     *
     * allowed object is
     * {@link AgreementSpecCharacteristicValue}
     */
    public List<AgreementSpecCharacteristicValue> getSpecCharacteristicValues() {
        if(specCharacteristicValues==null){
            specCharacteristicValues = new ArrayList<AgreementSpecCharacteristicValue>();
        }
        return specCharacteristicValues;
    }

    /**
     *
     *
     * @param specCharacteristicValues
     *
     *
     */
    public void setSpecCharacteristicValues(List<AgreementSpecCharacteristicValue> specCharacteristicValues) {
        this.specCharacteristicValues = specCharacteristicValues;
    }


    @Override
    public String toString() {
        return "AgreementSpecCharacteristic{" +
                "configurable=" + configurable +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", validFor=" + validFor +
                ", valueType='" + valueType + '\'' +
                ", specCharacteristicValues=" + specCharacteristicValues +
                '}';
    }
}
