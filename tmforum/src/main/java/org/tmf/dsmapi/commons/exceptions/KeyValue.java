package org.tmf.dsmapi.commons.exceptions;

/**
 * This is a placeholder class for passing parameters to the error logging routine
 * The class is a very simple key-value store, used by the FunctionalException class
 * Currently not in active use i.e. redundant
 **/
// Could we not just use HashMap<String, String>? Why create a new class?

public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
