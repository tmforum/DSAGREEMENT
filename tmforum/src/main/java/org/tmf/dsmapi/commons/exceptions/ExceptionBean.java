package org.tmf.dsmapi.commons.exceptions;

/**
* This class is used by the ExceptionType Enum, to log the error code and title
* For codes and titles, refer to ExceptionType.java
**/

public class ExceptionBean {
    
    private String code;
    private String title;
    
    public ExceptionBean(String code, String title) {
        this.code=code;
        this.title=title;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
}
