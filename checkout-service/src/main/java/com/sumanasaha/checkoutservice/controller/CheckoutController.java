package com.sumanasaha.checkoutservice.controller;


import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumanasaha.checkoutservice.bo.Basket;
import com.sumanasaha.checkoutservice.service.CheckoutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping( "/api/v1/checkout" )
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Operation( summary = "Adds product to basket with customerId and productId as request parameters ",
                description = "In this endpoint implementation, if the product is not found error will "
                              + "be returned.</br> In case customerIds with value blank or null, error will"
                              + "be returned.</br> In happy path where productId is found and valid, product"
                              + "will be successfully added to basket.</br> Valid productIds are WF123, WF1234,"
                              + " WF12345." )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "201",
                          description = "Product added to basket",
                          content = { @Content( mediaType = "application/json" ) } ),
            @ApiResponse( responseCode = "400",
                          description = "Invalid request parameters",
                          content = @Content )
    } )
    @PostMapping( value = "/addProduct", produces = "application/json" )
    public ResponseEntity<String> addProductToBasket( @RequestParam( value = "customerId" ) String customerId,
                                                      @RequestParam( value = "productId" ) String productId ) {

        String basketId = checkoutService.addProductToBasket( customerId, productId );
        return new ResponseEntity<>( "Product added successfully in the basket having id " + basketId + " !!", HttpStatus.CREATED );

    }

    @Operation( summary = "Removes product from basket with customerId and productId as request parameters ",
                description = "In this endpoint implementation, if the product is not found error will "
                              + "be returned.</br> In case customerIds with value blank or null, error will"
                              + "be returned.</br> In happy path where productId is found and valid, product"
                              + "will be successfully removed from basket.</br> Valid productIds are WF123,"
                              + " WF1234,WF12345." )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200",
                          description = "Product removed from basket",
                          content = { @Content( mediaType = "application/json" ) } ),
            @ApiResponse( responseCode = "400",
                          description = "Invalid request parameters",
                          content = @Content )
    } )
    @PutMapping( value = "/removeProduct", produces = "application/json" )
    public ResponseEntity<String> removeProductFromBasket( @RequestParam( value = "customerId" ) String customerId,
                                                           @RequestParam( value = "productId" ) String productId ) {

        String basketId = checkoutService.removeProductFromBasket( customerId, productId );
        return new ResponseEntity<>( "Product removed successfully from basket having id " + basketId + " !!", HttpStatus.OK );

    }

}
