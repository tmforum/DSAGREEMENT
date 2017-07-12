package com.amdocs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by atinsingh on 6/22/17.
 */
@Entity
public class Contact {

    @Id
    @GeneratedValue
    int id;
    String contactName;
    String contactCity;

    public Contact(String contactName, String contactCity) {
        this.contactName = contactName;
        this.contactCity = contactCity;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactCity(String contactCity) {
        this.contactCity = contactCity;
    }
}
