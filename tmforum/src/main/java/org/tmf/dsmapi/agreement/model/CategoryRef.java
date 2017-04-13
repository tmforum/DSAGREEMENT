package org.tmf.dsmapi.agreement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "CATEGORY_REF")
@Inheritance(strategy = InheritanceType.JOINED)
public class CategoryRef{

    @Id
    @Column(name = "CATEGORY_REF_ID_PK")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Unique value of service category
    protected  String id;

    //Unique reference of the category
    protected String href;

    //Name of the category.
    protected String name;

    //Category version.
    protected String version;


    public CategoryRef() {
    }



    /**
     * Return the ID of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */


    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *
     * allowed object is
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

    public String getHref() {
        return href;
    }

    /**
     * Set an HREF string for the object
     * @param href
     *
     * allowed object is
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

    public String getName() {
        return name;
    }

    /**
     * Set the name of object
     * @param name
     *
     * allowed object is
     * {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Version of the object
     * @return
     *
     * allowed object is
     * {@link String}
     */

    public String getVersion() {
        return version;
    }

    /**
     * Set the version of the object
     * @param version
     *
     * allowed object is
     * {@link String}
     */
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CategoryRef{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
