package org.tmf.dsmapi.agreementmanagement.agreementspecification.model;

import org.tmf.dsmapi.agreementmanagement.agreement.model.TimePeriod;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atinsingh on 3/20/17.
 */

@Entity(name = "AgreementSpecsCharacteristic")
@Table(name = "AGREEMENT_SPEC_CHARACTERISTIC")
public class AgreementSpecCharacteristic implements Serializable {

    protected Boolean configurable;

    protected String description;

    protected String name;

    protected TimePeriod validFor;

    protected String valueType;

    protected List<AgreementSpecCharacteristicValue> specCharacteristicValues;

    protected List<RelatedPartyRef> relatedParty;

    public AgreementSpecCharacteristic() {
    }


    /**
     * Return if specs are configurable
     * @return
     *
     * allowed object is
     * {@link Boolean}
     */
    @Basic
    @Column(name = "CONFIGURABLE")
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
    @Basic
    @Column(name = "DESCRIPTION", length = 255)
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
    @Basic
    @Column(name = "NAME", length = 255)
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
    @Embedded
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

    @Basic
    @Column(name = "VALUE_TYPE", length = 255)
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
    @OneToMany(targetEntity = AgreementSpecCharacteristicValue.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "SPEC_CHAR_VALUE_SPEC_CHARACTERISTIC_ID")
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

    /**
     * Return Related parties associated with Specs Characteristic
     * get/set will be allowed
     * @return
     *
     * allowed object is
     * {@link RelatedPartyRef}
     */
    @OneToMany(targetEntity = RelatedPartyRef.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "RELATED_PARTY_SPECIFICATION_CHAR_ID")
    public List<RelatedPartyRef> getRelatedParty() {
        return relatedParty;
    }

    /**
     *
     * @param relatedParty
     *
     */
    public void setRelatedParty(List<RelatedPartyRef> relatedParty) {
        this.relatedParty = relatedParty;
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
                ", relatedParty=" + relatedParty +
                '}';
    }
}
