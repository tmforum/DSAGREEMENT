package org.tmf.dsmapi.agreementmanagement.agreementspecification.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Created by atinsingh on 3/21/17.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "AgreementAttachment")
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

