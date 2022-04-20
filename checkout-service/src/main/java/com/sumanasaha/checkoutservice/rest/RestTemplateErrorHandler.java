package com.sumanasaha.checkoutservice.rest;


import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.sumanasaha.checkoutservice.exception.NotFoundException;


@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError( final ClientHttpResponse response ) throws IOException {
        return (
                response.getStatusCode().series() == CLIENT_ERROR
                || response.getStatusCode().series() == SERVER_ERROR );
    }

    @Override
    public void handleError( final ClientHttpResponse httpResponse ) throws IOException {

        if( httpResponse.getStatusCode()
                    .series() == HttpStatus.Series.SERVER_ERROR ) {
            // handle SERVER_ERROR
        }
        else if( httpResponse.getStatusCode()
                         .series() == HttpStatus.Series.CLIENT_ERROR ) {
            // handle CLIENT_ERROR
            if( httpResponse.getStatusCode() == HttpStatus.NOT_FOUND ) {
                throw new NotFoundException( "Not found!" );
            }
        }

    }
}
