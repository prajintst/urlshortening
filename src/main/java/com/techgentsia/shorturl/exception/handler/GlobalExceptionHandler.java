package com.techgentsia.shorturl.exception.handler;

import com.techgentsia.shorturl.constants.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handle all exception in application
 *
 * @author <a href="mailto:prajin@techgentsia.com">Prajin Prakash</a>
 * @version $Revision: 1.0 $
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    final
    MessageSource messageSource;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale local = request.getLocale();
        Map<String, String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(error -> ((FieldError) error).getField(),
                        error -> messageSource.getMessage(error.getDefaultMessage(),
                                null, local)));
        String errorMessage  = messageSource.getMessage(MessageConstants.VALIDATION_FAILED_ERROR,
                    new String[]{String.valueOf(errors.size())}, local);
        Object details = errors;

        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessage, details);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request, Locale locale) {
        String message = messageSource.getMessage(MessageConstants.ERROR_PREFIX +ex.getMessage(), ex.getArgs(), locale);
        log.error("CustomException",ex);
        return new ResponseEntity<>(new ErrorDetails(new Date(),
                message, request.getDescription(false)),ex.getResponseStatus());
    }

    // Handle all other exceptions as server error
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(),
                ex.getMessage(), request.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    // Handle all other exceptions as server error
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        log.error("Exception",ex);
        return new ResponseEntity<>(new ErrorDetails(new Date(),
                ex.getMessage(), request.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
