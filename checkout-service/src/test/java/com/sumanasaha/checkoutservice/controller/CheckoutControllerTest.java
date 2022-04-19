package com.sumanasaha.checkoutservice.controller;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.sumanasaha.checkoutservice.service.CheckoutServiceImpl;


@WebMvcTest( CheckoutController.class )
public class CheckoutControllerTest {

    //Test data
    private static final String CUSTOMER_ID_PARAM = "customerId";
    private static final String CUSTOMER_ID = "JohnSmith@2021";
    private static final String PRODUCT_ID_PARAM = "productId";
    private static final String PRODUCT_ID = "WF123";
    private static final String BASKET_ID = "Test_Basket_1";

    @MockBean
    private CheckoutServiceImpl checkoutService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddProductToBasket() throws Exception {

        when( checkoutService.addProductToBasket( CUSTOMER_ID, PRODUCT_ID )).thenReturn( BASKET_ID );

        mockMvc.perform( post( "/api/v1/checkout/addProduct?"
                              + CUSTOMER_ID_PARAM + "=" + CUSTOMER_ID
                              + "&" + PRODUCT_ID_PARAM + "=" + PRODUCT_ID ) )
                .andExpect( status().isCreated() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( content().string( containsString( BASKET_ID ) ) );

        verify( checkoutService ).addProductToBasket( CUSTOMER_ID, PRODUCT_ID );
        verifyNoMoreInteractions( checkoutService );
    }

    @Test
    public void testAddProductToBasketForInvalidRequestParam() throws Exception {

        mockMvc.perform( post( "/api/v1/checkout/addProduct") )
                .andExpect( status().isBadRequest() );
        verifyNoInteractions( checkoutService );

    }

    @Test
    public void testRemoveProductFromBasket() throws Exception {

        when( checkoutService.removeProductFromBasket( CUSTOMER_ID, PRODUCT_ID )).thenReturn( BASKET_ID );

        mockMvc.perform( put( "/api/v1/checkout/removeProduct?"
                              + CUSTOMER_ID_PARAM + "=" + CUSTOMER_ID
                              + "&" + PRODUCT_ID_PARAM + "=" + PRODUCT_ID ) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( content().string( containsString( BASKET_ID ) ) );

        verify( checkoutService ).removeProductFromBasket( CUSTOMER_ID,PRODUCT_ID );
        verifyNoMoreInteractions( checkoutService );

    }

    @Test
    public void testRemoveProductToBasketForInvalidRequestParam() throws Exception {

        mockMvc.perform( put( "/api/v1/checkout/removeProduct") )
                .andExpect( status().isBadRequest() );
        verifyNoInteractions( checkoutService );

    }

}