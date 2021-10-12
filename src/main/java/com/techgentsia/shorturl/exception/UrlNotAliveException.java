package com.techgentsia.shorturl.exception;

import com.techgentsia.shorturl.exception.common.NotFoundException;

public class UrlNotAliveException extends NotFoundException {
    public UrlNotAliveException(){
        super(UrlNotAliveException.class.getSimpleName());
    }
}
