package org.tmf.dsmapi.agreement.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


//@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "RELATED_PARTY_REF")
@Table(name = "RELATED_PARTY_REF")
@Inheritance(strategy = InheritanceType.JOINED)
public class RelatedPartyRef {
    protected String id;
    protected String href;
    protected String name;
    protected String role;
    protected TimePeriod validFor;

    public RelatedPartyRef() {
    }

    /**
     * Return the ID of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    /**
     * Sets id for the object
     * @param id
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the HREF of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "HREF", length = 255)
    public String getHref() {
        return href;
    }


    /**
     * Sets id for the object
     * @param href
     *
     * allowed object is
     *
     * {@link String}
     */

    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Return the Name of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "NAME_")
    public String getName() {
        return name;
    }


    /**
     * Sets id for the object
     * @param name
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Role of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "ROLE_")
    public String getRole() {
        return role;
    }


    /**
     * Sets id for the object
     * @param role
     *
     * allowed object is
     *
     * {@link String}
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Return the TimePeriod object embedded in the object
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
     * Sets id for the object
     * @param validFor
     *
     * allowed object is
     *
     * {@link TimePeriod}
     */

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    @Override
    public String toString() {
        return "RelatedPartyRef{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
