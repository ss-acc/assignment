package com.sumanasaha.checkoutservice.exception;


public class NotFoundException extends RuntimeException{

    public NotFoundException( final String message ) {
        super( message );
    }
}
