package com.publicis.sapient.p2p.exception.handler;

import com.publicis.sapient.p2p.builder.ServiceResponseBuilder;
import com.publicis.sapient.p2p.exception.BusinessException;
import com.publicis.sapient.p2p.exception.CustomException;
import com.publicis.sapient.p2p.vo.ServiceResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageSource msgSource;

    @Autowired
    private ServiceResponseBuilder responseBuilder;

    /**
     * The BusinessException exception handler to handle BusinessException
     *
     * @param ex - The BusinessException
     * @return the error response with appropriate  HTTP status code
     */
    @ExceptionHandler({CustomException.class, BusinessException.class})
    public ResponseEntity<ServiceResponse> handleMeshException(CustomException ex) {
        ServiceResponse svcResponse;
        BindingResult validationErrors = ex.getValidationErrors();
        Locale currentLocale = LocaleContextHolder.getLocale();
        if (validationErrors != null) {
            svcResponse = responseBuilder.buildValidationErrorResponse(validationErrors, msgSource, currentLocale);
        } else {
            svcResponse = responseBuilder.buildErrorResponse(ex);
        }

        return switch (ex.getErrorCode()) {
            case SERVICE_NOT_AVAILABLE -> new ResponseEntity<>(svcResponse, HttpStatus.SERVICE_UNAVAILABLE);
            case BAD_REQUEST -> new ResponseEntity<>(svcResponse, HttpStatus.BAD_REQUEST);
            case UNAUTHORIZED -> new ResponseEntity<>(svcResponse, HttpStatus.UNAUTHORIZED);
            case UNAUTHENTICATED -> new ResponseEntity<>(svcResponse, HttpStatus.FORBIDDEN);
            case NOT_FOUND -> new ResponseEntity<>(svcResponse, HttpStatus.NOT_FOUND);
            case CONFLICT -> new ResponseEntity<>(svcResponse, HttpStatus.CONFLICT);
            default -> new ResponseEntity<>(svcResponse, HttpStatus.NOT_IMPLEMENTED);
        };

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ServiceResponse> handlePatchException(HttpMessageNotReadableException ex) {
        logger.error(MessageFormat.format("Invalid Request Body : {0} : {1}",ex.getMessage(), ex.getClass()));
        ServiceResponse svcResponse = new ServiceResponse();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("BAD_REQUEST", "Invalid Request Body");
        svcResponse.setErrorMap(errorMap);

        return new ResponseEntity<>(svcResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ServiceResponse> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        logger.error(MessageFormat.format("Content-Type  is not supported : {0} : {1}", ex.getMessage(), ex.getClass()));
        ServiceResponse svcResponse = new ServiceResponse();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("BAD_REQUEST", MessageFormat.format("Content-Type  is not supported : {0}",ex.getMessage()));
        svcResponse.setErrorMap(errorMap);

        return new ResponseEntity<>(svcResponse, HttpStatus.BAD_REQUEST);

    }

    /**
     * The global exception handler to handle all the exceptions other than MeshException
     *
     * @param exception - The exception
     * @return the error response with 500 HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ServiceResponse> handleOtherException(Exception exception) {

        logger.info("Global Exception Handler unhandled exception : " + exception.getMessage());
        String stacktrace = ExceptionUtils.getStackTrace(exception);
        logger.info("****************** Global Exception Handler Stcktrace ********* : " + stacktrace);


        ServiceResponse svcResponse = null;
        if (exception instanceof BindException) {
            BindingResult bindingResult = ((BindException) exception).getBindingResult();
            Locale currentLocale = LocaleContextHolder.getLocale();
            svcResponse = responseBuilder.buildValidationErrorResponse(bindingResult, msgSource, currentLocale);
            return new ResponseEntity<>(svcResponse, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
            Locale currentLocale = LocaleContextHolder.getLocale();
            svcResponse = responseBuilder.buildValidationErrorResponse(bindingResult, msgSource, currentLocale);
            return new ResponseEntity<>(svcResponse, HttpStatus.BAD_REQUEST);
        } else {
            svcResponse =  responseBuilder.buildErrorResponse(exception);
        }

        return new ResponseEntity<>(svcResponse, HttpStatus.SERVICE_UNAVAILABLE);

    }

}