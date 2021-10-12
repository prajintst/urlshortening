package com.techgentsia.shorturl.exception.common;

import com.techgentsia.shorturl.exception.handler.CustomException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(String messageKey) {
        super(messageKey, HttpStatus.NOT_FOUND);
    }
}
