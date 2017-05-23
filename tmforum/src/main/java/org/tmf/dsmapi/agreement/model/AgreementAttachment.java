package org.tmf.dsmapi.agreement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@SuppressWarnings("all")
@Entity
@Table(name = "AGREEMENT_ATTACHMENT")
public class AgreementAttachment {


    @Id
    @GeneratedValue
    //Unique identifier for attachement
    @Column(name = "ATTACHMENT_ID_PK")
    protected String id;

    //reference to the attachment
    @Column
    protected String href;

    //type of attachment (picture/video)
    @Column
    protected String type;

    //URL of the attachment
    @Column
    protected String url;


    public AgreementAttachment() {
    }


    /**
     * Return the id of attachment object
     *
     * @return allowed Object is
     * {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of the object
     *
     * @param id allowed object is
     *           {@link String}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets HREF for the object
     *
     * @return allowed object is
     * {@link String}
     */
    @Column
    public String getHref() {
        return href;
    }

    /**
     * Sets HREF in the object
     *
     * @param href allowed object is
     *             {@link String}
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * return the type of attachement object
     *
     * @return allowed object is
     * {@link String}
     */

    public String getType() {
        return type;
    }

    /**
     * Set the type
     *
     * @param type allowed object is
     *             {@link String}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the URL of the attachment
     *
     * @return allowed object is
     * {@link String}
     */

    public String getUrl() {
        return url;
    }

    /**
     * Set the URL
     *
     * @param url allowed object is
     *            {@link String}
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

