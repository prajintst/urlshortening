package com.techgentsia.shorturl.exception;

import com.techgentsia.shorturl.exception.common.NotFoundException;

public class UrlIdNotFoundException extends NotFoundException {
    public UrlIdNotFoundException(){
        super(UrlIdNotFoundException.class.getSimpleName());
    }
}
