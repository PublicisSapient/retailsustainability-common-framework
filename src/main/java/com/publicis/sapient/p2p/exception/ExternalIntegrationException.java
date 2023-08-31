package com.publicis.sapient.p2p.exception;

import com.publicis.sapient.p2p.exception.util.ErrorCode;
import org.springframework.validation.BindingResult;

public class ExternalIntegrationException extends CustomException {

	private static final long serialVersionUID = 1814648470287126564L;

	private final ErrorCode errorCode;
	
	private BindingResult validationErrors;

	/**
	 * @param pErrorCode
	 * @param pMessage
	 */
	public ExternalIntegrationException(ErrorCode pErrorCode, String pMessage) {
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
	public ExternalIntegrationException(ErrorCode pErrorCode, String pMessage, BindingResult validationErrors) {
		super(pMessage);
		errorCode = pErrorCode;
		this.validationErrors = validationErrors;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public ExternalIntegrationException(ErrorCode pErrorCode, String pMessage, Throwable e) {
		super(pMessage, e);
		errorCode = pErrorCode;
		validationErrors = null;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public ExternalIntegrationException(ErrorCode pErrorCode, Throwable e) {
		super(pErrorCode, e);
		errorCode = pErrorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public BindingResult getValidationErrors() {
		return validationErrors;
	}
}
