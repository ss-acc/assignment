package com.sumanasaha.checkoutservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition( info = @Info( version = "V1.0.0", title = "Checkout Service API" ) )
@SpringBootApplication
public class CheckoutServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run( CheckoutServiceApplication.class, args );
    }

}
