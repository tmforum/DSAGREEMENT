package org.tmf.dsmapi.agreement.model;

/**
 * Created by atinsingh on 3/20/17.
 */
public class Characteristic {
    protected String name;
    protected String value;

    public Characteristic(String name, String value) {
        this.name = name;
        this.value = value;
    }

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

    @Override
    public String toString() {
        return "Characteristic{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
