package com.publicis.sapient.p2p.exception.util;

public enum ErrorCode {
	
	SERVICE_NOT_AVAILABLE(503),
	BAD_REQUEST(400),
	UNAUTHORIZED(401),
	UNAUTHENTICATED(403),
	NOT_FOUND(404),
	CONFLICT(409);
	
	private final int errorCode;
	
	private ErrorCode(int errorCode) {
		this.errorCode=errorCode;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
}
