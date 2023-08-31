package com.publicis.sapient.p2p.vo;

import java.util.Date;

/**
 * AuditReport class will hold the properties required for preparing Audit
 * report
 * 
 * @author Sapient
 * 
 */
public class AuditReport {
    /**
     * Property used to hold the mAuditReqResponse.
     */
    private AuditReqResponse auditReqResponse;
       
    private String createDate;
    
   
    /**
     * property to hold serverName
     */
    private String serviceName;
    /**
     * property to hold mApiURL
     */
    private String apiURL;
    /**
     * property to hold Status
     */
    private boolean status;
    /**
     * property to hold AuditEventTypeId
     */
   
    /**
     * property to hold roundTripTime
     */
    private Long roundTripTime;
    /**
     * property to hold serverName
     */
    private String serverName;
    /**
     * property to hold eventDate
     */
    private Date eventDate;
    /**
     * property to hold auditRequestUri
     */
    /**
     * property to hold loggedInUserId
     */
    private String loggedInUserId;
    /**
     * property to hold responseCode
     */
    private String responseCode;
    /**
     * property to hold selectedDevice
     */
  
    private String accountType;
   
    private String orderId;
      /**
     * property to hold SubServiceName
     */
    private String subServiceName;
   

    private String jvmName;

  

    private String originatingSystem;

    private String startTime;

    private String endTime;

  

    private String statusCode;

  
    private String appVersion;

    private String apiVersion;

    private String warmUp;
    
   
	private String errorMapCode;
    
    private String errorMapMessage;
    
    private String statusMessage;

	public String flowName;
	
	public String activityType;
	
	public String correlationId;
	
	private String systemName;

	public AuditReqResponse getAuditReqResponse() {
		return auditReqResponse;
	}

	public void setAuditReqResponse(AuditReqResponse auditReqResponse) {
		this.auditReqResponse = auditReqResponse;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getRoundTripTime() {
		return roundTripTime;
	}

	public void setRoundTripTime(Long roundTripTime) {
		this.roundTripTime = roundTripTime;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSubServiceName() {
		return subServiceName;
	}

	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}

	public String getJvmName() {
		return jvmName;
	}

	public void setJvmName(String jvmName) {
		this.jvmName = jvmName;
	}

	public String getOriginatingSystem() {
		return originatingSystem;
	}

	public void setOriginatingSystem(String originatingSystem) {
		this.originatingSystem = originatingSystem;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getWarmUp() {
		return warmUp;
	}

	public void setWarmUp(String warmUp) {
		this.warmUp = warmUp;
	}

	public String getErrorMapCode() {
		return errorMapCode;
	}

	public void setErrorMapCode(String errorMapCode) {
		this.errorMapCode = errorMapCode;
	}

	public String getErrorMapMessage() {
		return errorMapMessage;
	}

	public void setErrorMapMessage(String errorMapMessage) {
		this.errorMapMessage = errorMapMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
  

}
