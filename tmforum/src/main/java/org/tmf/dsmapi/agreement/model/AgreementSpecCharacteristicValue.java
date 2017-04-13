package org.tmf.dsmapi.agreement.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_SPEC_CHARACTERISTIC_VALUE")
@XmlRootElement
public class AgreementSpecCharacteristicValue{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_SPEC_CHAR_VALUE_ID_PK")
    protected String id; 

    @Column(name = "DEFAULT_VALUE")
    protected  Boolean _default;

    protected String unitOfMeasure;

	@Embedded
    protected TimePeriod validFor;

    protected String value;

    protected String valueFrom;

    protected String valueTo;

    protected String valueType;

    public AgreementSpecCharacteristicValue() {
    }

    /**
     * Return flag to determine if its a default value
     * @return
     *
     * allowed object is
     * {@link Boolean}
     */
    public Boolean getdefault() {
        return _default;
    }

    /**
     * Set flag default as true/false
     * @param _default
     *
     * allowed object is
     * {@link Boolean}
     */
    public void setdefault(Boolean _default) {
        this._default = _default;
    }

    /**
     * Return the unit of measure for the object
     * @return
     *
     * allowed object is
     *
     * {@link String}
     */

    @Basic
    @Column(name = "MEASURE_UNIT", length = 255)
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of unit for measure
     * @param unitOfMeasure
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Set an timeperiod for the object
     * @return
     *
     * allowed object is
     * {@link TimePeriod}
     */
    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     *
     * Set the time period
     *
     * @param validFor
     *
     * allowed object is
     * {@link TimePeriod}
     */
    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * Returns the value of the AgreementSpecificationCharacteristic
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param value
     *
     * allowed object is
     * {@link String}
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the SourceFrom  of the AgreementSpecificationCharacteristic
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getValueFrom() {
        return valueFrom;
    }

    /**
     * Set the value of from source
     * @param valueFrom
     *
     * allowed object is
     * {@link String}
     */
    public void setValueFrom(String valueFrom) {
        this.valueFrom = valueFrom;
    }

    /**
     * Returns the valueTo of the AgreementSpecificationCharacteristic
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getValueTo() {
        return valueTo;
    }


    /**
     * Set the value of To source
     * @param valueTo
     *
     * allowed object is
     * {@link String}
     */
    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }


    /**
     * Returns the valueType of the AgreementSpecificationCharacteristic
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getValueType() {
        return valueType;
    }


    /**
     * Set the valueType of the object
     * @param valueType
     *
     * allowed object is
     * {@link String}
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return "AgreementSpecCharacteristicValue{" +
                "_default=" + _default +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", validFor=" + validFor +
                ", value='" + value + '\'' +
                ", valueFrom='" + valueFrom + '\'' +
                ", valueTo='" + valueTo + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
