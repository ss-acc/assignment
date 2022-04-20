package com.sumanasaha.checkoutservice.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumanasaha.checkoutservice.bo.Basket;
import com.sumanasaha.checkoutservice.bo.Product;


@RestClientTest( CheckoutServiceImpl.class )
public class CheckoutServiceTest {

    private static final String CUSTOMER_ID = "123";
    private static final String PRODUCT_ID = "WF123";
    private static final String PRODUCT_NAME = "Sofa";
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal( "549.99" );
    private static final String PRODUCT_OFFER = "2for1";
    private static final String PRODUCT_SERVICE_URI = "http://localhost:8081/api/v1/product/";

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @SpyBean
    private CheckoutServiceImpl checkoutService;

    private  Map<String, Basket> customerToBasketMap;

    private ObjectMapper mapper = new ObjectMapper();

    private Product product;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks( this );
        customerToBasketMap = new ConcurrentHashMap<>();
        product = Product.builder()
                .id( PRODUCT_ID )
                .name( PRODUCT_NAME )
                .price( PRODUCT_PRICE )
                .offers( List.of( PRODUCT_OFFER ) )
                .build();

    }

    @Test
    public void testAddProductToBasket() throws JsonProcessingException {

        this.mockRestServiceServer
                .expect( requestTo( PRODUCT_SERVICE_URI + PRODUCT_ID ) )
                .andRespond( withSuccess( mapper.writeValueAsString( product ), MediaType.APPLICATION_JSON ) );

        assertThat( checkoutService.addProductToBasket( CUSTOMER_ID, PRODUCT_ID ) ).contains( CUSTOMER_ID );

    }

    @Test
    public void testRemoveProductFromBasket() {

        Basket basket = mock( Basket.class );
        List<Product> products = Arrays.asList( product );
        customerToBasketMap.put( CUSTOMER_ID, basket );
        when( basket.getProducts() ).thenReturn( products );

        assertThat( checkoutService.removeProductFromBasket( CUSTOMER_ID, PRODUCT_ID ) ).contains( CUSTOMER_ID );

    }
}