package org.tmf.dsmapi.commons.exceptions;

public class BadUsageException extends FunctionalException {

    // Calls the FunctionalException constuctor with the exception type
    public BadUsageException(ExceptionType type) {
        super(type);
    }

    // Calls the FunctionalException constuctor with the exception type and message
    public BadUsageException(ExceptionType type, String message) {
        super(type, message);
    }

}
