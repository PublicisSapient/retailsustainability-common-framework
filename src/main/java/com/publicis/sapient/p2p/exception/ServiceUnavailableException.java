package com.publicis.sapient.p2p.exception;

import com.publicis.sapient.p2p.exception.util.ErrorCode;

public class ServiceUnavailableException extends CustomException {

	private static final long serialVersionUID = 1814648470287126564L;

	private final ErrorCode errorCode;

	/**
	 * @param pErrorCode
	 * @param pMessage
	 */
	public ServiceUnavailableException(ErrorCode pErrorCode, String pMessage) {
		super(pMessage);
		errorCode = pErrorCode;
	}

	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public ServiceUnavailableException(ErrorCode pErrorCode, String pMessage, Throwable e) {
		super(pErrorCode,pMessage, e);
		errorCode = pErrorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
