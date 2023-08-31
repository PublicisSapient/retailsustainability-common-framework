package com.publicis.sapient.p2p.exception;


import com.publicis.sapient.p2p.exception.util.ErrorCode;

/**
 * The CoCDataNotFoundException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to our online documentation.
 *
 * @author ashabrol
 */
public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 7718345512143293558L;

    private ErrorCode errorCode;

    public DataNotFoundException() {
        super();
    }

    /**
     * Create a business exception object with error information
     *
     * @param message
     */
    public DataNotFoundException(String message) {
        super(message);
    }

    /**
     * Create a business exception object with error code, error code
     *
     * @param message
     * @param errorCode
     */
    public DataNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Create a business exception object with error information, exception object, error code
     *
     * @param message
     * @param cause
     * @param errorCode
     */
    public DataNotFoundException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Create a business exception object with error information, exception object, error code
     *
     * @param cause
     * @param errorCode
     */
    public DataNotFoundException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * @return errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

}