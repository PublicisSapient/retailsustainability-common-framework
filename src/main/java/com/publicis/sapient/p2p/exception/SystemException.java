package com.publicis.sapient.p2p.exception;

import com.publicis.sapient.p2p.exception.util.ErrorCode;

/**
 * The CoCSystemException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to our online documentation.
 *
 * @author ashabrol
 */
public class SystemException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private ErrorCode errorCode;

    public SystemException() {
        super();
    }

    /**
     * Create a business exception object with error information
     *
     * @param message
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Create a business exception object with error code, error code
     *
     * @param message
     * @param errorCode
     */
    public SystemException(String message, ErrorCode errorCode) {
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
    public SystemException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Create a business exception object with error information, exception object, error code
     *
     * @param cause
     * @param errorCode
     */
    public SystemException(Throwable cause, ErrorCode errorCode) {
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