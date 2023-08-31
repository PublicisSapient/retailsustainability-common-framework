package com.publicis.sapient.p2p.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class BaseValidator {
	
	public void isValidEUPFlow(String flow, Errors errors){
		boolean isvalidFlow = false;
		if(!StringUtils.isEmpty(flow) && "EUP".equalsIgnoreCase(flow)){
			isvalidFlow = true;
		}
		if(!isvalidFlow){
			errors.rejectValue("flow", "flow.invalid");
		}		
	}
	
	public void validateEmptyDeviceProdID(Errors e){
		ValidationUtils.rejectIfEmpty(e, "deviceProdId", "deviceProdId.empty");	
	}
	
	public void validateJSONData(Errors e){
		ValidationUtils.rejectIfEmpty(e, "jsonData", "jsonData.empty");	
	}

}
