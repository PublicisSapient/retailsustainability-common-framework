package com.publicis.sapient.p2p.exception;

import com.publicis.sapient.p2p.exception.util.ErrorCode;
import org.springframework.validation.BindingResult;

public class CustomException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3770003796986186393L;

	private final ErrorCode errorCode;
	
	private BindingResult validationErrors;
	
	public CustomException(String pMessage) {
		super(pMessage);
		errorCode = null;
		validationErrors = null;
	}

	/**
	 * @param pErrorCode
	 * @param pMessage
	 */
	public CustomException(ErrorCode pErrorCode, String pMessage) {
		super(pMessage);
		errorCode = pErrorCode;
		validationErrors = null;
	}
	
	/**
	 * 
	 * @param pErrorCode
	 * @param pMessage
	 * @param validationErrors
	 */
	public CustomException(ErrorCode pErrorCode, String pMessage, BindingResult validationErrors) {
		super(pMessage);
		errorCode = pErrorCode;
		this.validationErrors = validationErrors;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public CustomException(ErrorCode pErrorCode, String pMessage, Throwable e) {
		super(pMessage, e);
		errorCode = pErrorCode;
		validationErrors = null;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public CustomException(String pMessage, Throwable e) {
		super(pMessage, e);
		errorCode = null;
		validationErrors = null;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public CustomException(ErrorCode pErrorCode, Throwable e) {
		super(e);
		errorCode = pErrorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public BindingResult getValidationErrors() {
		return validationErrors;
	}
}
