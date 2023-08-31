package com.publicis.sapient.p2p.builder;

import com.publicis.sapient.p2p.exception.BusinessException;
import com.publicis.sapient.p2p.exception.CustomException;
import com.publicis.sapient.p2p.vo.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@Primary
public class ServiceResponseBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResponseBuilder.class);

    /**
     * @param errors
     * @return
     */
    public ServiceResponse buildValidationErrorResponse(Errors errors) {
        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode("04");
        Map<String, String> errorMap = new HashMap<String, String>();
        for (ObjectError error : errors.getAllErrors()) {
            errorMap.put(error.getCode(), error.getObjectName());
        }
        svcResp.setErrorMap(errorMap);
        return svcResp;
    }

    /**
     * @param e
     * @return
     */
    public ServiceResponse buildErrorResponse(BusinessException e) {
        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode(e.getErrorCode());
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put(e.getErrorCode().toString(), e.getMessage());
        svcResp.setErrorMap(errorMap);
        return svcResp;
    }

    /**
     * @param e
     * @return
     */
    public ServiceResponse buildErrorResponse(CustomException e) {

        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode(e.getErrorCode());
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put(e.getErrorCode().toString(), e.getMessage());
        svcResp.setErrorMap(errorMap);
        return svcResp;

		/*ServiceResponse svcResp = new ServiceResponse();
		//svcResp.setStatusCode("99");
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("99", e.getMessage());
		svcResp.setErrorMap(errorMap);
		return svcResp;*/
    }

    /**
     * @param e
     * @return
     */
    public ServiceResponse buildErrorResponse(Exception e) {

        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode("99");
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("99", e.getMessage());
        svcResp.setErrorMap(errorMap);
        return svcResp;
    }


    /**
     *
     *
     * @param errors
     * @param msgSource
     * @param currentLocale
     * @return
     */
    public ServiceResponse buildValidationErrorResponse(BindingResult errors, MessageSource msgSource, Locale currentLocale) {
        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode("04");
        Map<String, String> errorMap = new HashMap<String, String>();
        for (ObjectError error : errors.getAllErrors()) {
            String msg = "DEFAULT_MESSAGE_BLANK";
            try {
                msg = msgSource.getMessage(error.getCode(), null, currentLocale);
            } catch (NoSuchMessageException noSuchMessageException) {
                LOGGER.error("Error while getting error message for error code :: " + error.getCode(), noSuchMessageException);
                msg = error.getDefaultMessage();
            }
            errorMap.put(error.getCode(), msg);
        }
        svcResp.setErrorMap(errorMap);
        return svcResp;
    }

    /**
     * @return
     */
    public ServiceResponse buildSuccessResponse() {
        ServiceResponse svcResp = new ServiceResponse();
        //svcResp.setStatusCode("00");
        //svcResp.setOutput(response);
        return svcResp;
    }
}
