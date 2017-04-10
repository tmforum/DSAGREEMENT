package org.tmf.dsmapi.agreement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "AGREEMENT_ATTACHMENT")
@Inheritance(strategy = InheritanceType.JOINED)
public class AgreementAttachment implements Serializable {

    protected String id;
    protected String href;
    protected String type;
    protected String url;

    public AgreementAttachment() {
    }

    public AgreementAttachment(String id, String href, String type, String url) {
        this.id = id;
        this.href = href;
        this.type = type;
        this.url = url;
    }

    /**
     * Return the id of attachment object
     * @return
     *
     * allowed Object is
     * {@link String}
     */
    @Id
    @Column(name = "ID", length = 255)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    /**
     * Sets id of the object
     *
     * @param id
     * allowed object is
     * {@link String}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets HREF for the object
     * @return
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "HREF", length = 255)
    public String getHref() {
        return href;
    }

    /**
     * Sets HREF in the object
     * @param href
     * allowed object is
     * {@link String}
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * return the type of attachement object
     * @return
     * allowed object is
     * {@link String}
     */
    @Basic
    @Column(name = "TYPE", length = 255)
    public String getType() {
        return type;
    }

    /**
     * Set the type
     * @param type
     * allowed object is
     * {@link String}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the URL of the attachment
     * @return
     * allowed object is
     * {@link String}
     */

    @Basic
    @Column(name = "ATTACHMENT_URL", length = 255)
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL
     * @param url
     *
     * allowed object is
     * {@link String}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AgreementAttachment{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

