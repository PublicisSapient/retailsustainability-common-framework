package com.publicis.sapient.p2p.exception;

import com.publicis.sapient.p2p.exception.util.ErrorCode;

public class DataAccessException extends CustomException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 */
	public DataAccessException(ErrorCode pErrorCode, String pMessage) {
		super(pMessage);
		errorCode = pErrorCode;
	}
	
	/**
	 * @param pErrorCode
	 * @param pMessage
	 * @param e
	 */
	public DataAccessException(ErrorCode pErrorCode, String pMessage, Throwable e) {
		super(pMessage, e);
		errorCode = pErrorCode;
	}
	
	public DataAccessException(ErrorCode pErrorCode, Throwable e) {
		super(pErrorCode, e);
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}

