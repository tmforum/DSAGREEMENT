package org.tmf.dsmapi.commons.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* Generic class for all functional exceptions. 
* - Functional exceptions must be checked exceptions
* - The FunctionalException class extends Exception class and provides a generic structure for logging exceptions
* - Known subclasses: BadUsageException, UnknownResourceException
* - Although not implmented in thsi application, exceptions can be logged along with the name of the class, method 
*   and other user speficied key-value parameters.
**/

public class FunctionalException extends Exception implements Serializable {

    private static final long serialVersionUID = 7552671441723224932L;
    private String localisationClass;
    private String localisationMethod;
    private ExceptionType type;
    private List<KeyValue> keyValue;

	// Each constructor calls the appropriate super() function i.e. constructor for Exception class

    public FunctionalException() {
        super();
        localisationClass = "";
        localisationMethod = "";
    }
    
    public FunctionalException(ExceptionType type) {
        super();
        this.type = type;
        localisationClass = "";
        localisationMethod = "";
    }
    
    public FunctionalException(ExceptionType type, String message) {
        super(message);
        this.type = type;
        localisationClass = "";
        localisationMethod = "";
    }    

    public FunctionalException(String message) {
        super(message);
        localisationClass = "";
        localisationMethod = "";
    }

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
        localisationClass = "";
        localisationMethod = "";
    }

    public FunctionalException(Throwable cause) {
        super(cause);
        localisationClass = "";
        localisationMethod = "";
    }

    public FunctionalException(String clazz, String method, String message) {
        super(message);
        localisationClass = clazz;
        localisationMethod = method;
    }

    public FunctionalException(String clazz, String method, String message, Throwable cause) {
        super(message, cause);
        localisationClass = clazz;
        localisationMethod = method;
    }

    public String getLocalisationClasse() {
        return localisationClass;
    }

    public String getLocalisationMethod() {
        return localisationMethod;
    }

    public String getLocalisation() {
        return localisationClass + "." + localisationMethod;
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }
    
    public List<KeyValue> getKeyValue() {
        return keyValue;
    }

	// This function adds a KeyValue object to the local KeyValue store
    public List<KeyValue> addKeyValue(KeyValue keyValue) {
        if (this.keyValue == null)  {
            this.keyValue = new ArrayList<KeyValue>();
        }
        this.keyValue.add(keyValue);
        return this.keyValue;
    }
    
	// This function adds the input key and value pair to the local KeyValue store
    public List<KeyValue> addKeyValue(String key, String value) {
        if (this.keyValue == null)  {
            this.keyValue = new ArrayList<KeyValue>();
        }        
        this.keyValue.add(new KeyValue(key, value));
        return this.keyValue;
    }     
}
