package com.publicis.sapient.p2p.exception.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Component
public class ErrorResolver {
	
	
	@Value("#{${errorMsgMap}}")
	private Map<String, String> errorMsgMap;

	public String getErrorMessage(String errMsg, String input) {

		// String errorMsg = errMsg + ":" + input ;
		String errorMsg = MessageFormat.format(errorMsgMap.get(errMsg), input);
		return errorMsg;
	}

	public String getErrorMessage(String errMsg, List<String> input) {
		String errorMsg = null;
		for (String s: input) {
			errorMsg = MessageFormat.format(errorMsgMap.get(errMsg), s);
		}
		return errorMsg;
	}

}
