package com.techgentsia.shorturl.exception;

import com.techgentsia.shorturl.exception.common.NotFoundException;

public class UrlNotFoundException extends NotFoundException {
    public UrlNotFoundException(){
        super(UrlNotFoundException.class.getSimpleName());
    }
}
