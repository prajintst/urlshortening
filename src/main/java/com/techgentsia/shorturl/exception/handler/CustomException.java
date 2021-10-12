package com.techgentsia.shorturl.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * Base class for all custom exceptions
 *
 * @author <a href="mailto:prajin@techgentsia.com">Prajin Prakash</a>
 * @version $Revision: 1.0 $
 */
public abstract class CustomException extends Exception {
    private static final long serialVersionUID = 1L;
    @Getter
    private final transient Object[] args;
    @Getter
    private final HttpStatus responseStatus;

    protected CustomException(String messageKey, HttpStatus httpStatus){
        this(messageKey,httpStatus,null);
    }

    protected CustomException(String messageKey, HttpStatus httpStatus, @Nullable Object[] args){
        super(messageKey);
        this.responseStatus = httpStatus;
        this.args = args;

    }

}
