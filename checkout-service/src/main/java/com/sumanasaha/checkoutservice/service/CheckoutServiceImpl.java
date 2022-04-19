package com.sumanasaha.checkoutservice.service;


import static com.sumanasaha.checkoutservice.bo.BasketStatus.CREATED;
import static com.sumanasaha.checkoutservice.bo.BasketStatus.UPDATED;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sumanasaha.checkoutservice.bo.Basket;
import com.sumanasaha.checkoutservice.bo.Product;
import com.sumanasaha.checkoutservice.rest.RestTemplateErrorHandler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class CheckoutServiceImpl implements CheckoutService {

    private static final String PRODUCT_SERVICE_URI = "http://localhost:8081/api/v1/product/";

    private final Map<String, Basket> customerToBasketMap = new ConcurrentHashMap<>();

    private final RestTemplate restTemplate;

    @Autowired
    public CheckoutServiceImpl( RestTemplateBuilder restTemplateBuilder ) {
        restTemplate = restTemplateBuilder
                .errorHandler( new RestTemplateErrorHandler() )
                .build();
    }

    @Override
    public String addProductToBasket( final String customerId, final String productId ) {
        Objects.requireNonNull( customerId );
        Objects.requireNonNull( productId );

        Product product = fetchProductById( productId );
        if( customerToBasketMap.get( customerId ) != null ) {
            Optional<Basket> currentBasket = updateBasket( customerId, product );
            currentBasket.ifPresent( basket -> customerToBasketMap.put( customerId, basket ) );
        }
        else {
            Basket basket = createBasket( customerId, product );
            customerToBasketMap.put( customerId, basket );
        }

        return customerToBasketMap.get( customerId ).getBasketId();
    }

    @Override
    public String removeProductFromBasket( final String customerId, final String productId ) {
        Objects.requireNonNull( customerId );
        Objects.requireNonNull( productId );

        Basket retrievedBasket = customerToBasketMap.get( customerId );
        List<Product> retrievedProducts = retrievedBasket.getProducts();

        List<Product> updatedProducts = retrievedProducts.stream().filter( product -> !product.getId().equals( productId ) )
                .collect( Collectors.toList() );

        retrievedBasket.setProducts( updatedProducts );
        retrievedBasket.setBasketAmount( calculateBasketAmount( updatedProducts ) );
        retrievedBasket.setTimeStamp( LocalDateTime.now() );
        retrievedBasket.setBasketStatus( UPDATED );

        customerToBasketMap.put( customerId, retrievedBasket );
        return customerToBasketMap.get( customerId ).getBasketId();

    }

    private Product fetchProductById( final String productId ) {

        return restTemplate.getForObject( PRODUCT_SERVICE_URI + productId, Product.class );
    }

    private Basket createBasket( final String customerId, final Product newProduct ) {

        AtomicInteger basketNumber = new AtomicInteger( 0 );
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add( newProduct );
        BigDecimal basketAmount = calculateBasketAmount( products );
        return Basket.builder()
                .basketId( customerId + "_" + basketNumber.incrementAndGet() )
                .products( products )
                .customerId( customerId )
                .timeStamp( LocalDateTime.now() )
                .basketAmount( basketAmount )
                .basketStatus( CREATED ).build();

    }

    private Optional<Basket> updateBasket( final String customerId, final Product product ) {

        Objects.requireNonNull( customerId );
        Objects.requireNonNull( product );

        Basket oldBasket = customerToBasketMap.get( customerId );
        List<Product> existingProducts = oldBasket.getProducts();
        existingProducts.add( product );
        oldBasket.setProducts( existingProducts );
        oldBasket.setTimeStamp( LocalDateTime.now() );
        oldBasket.setBasketAmount( calculateBasketAmount( existingProducts ) );
        oldBasket.setBasketStatus( UPDATED );

        return Optional.of( oldBasket );

    }

    private BigDecimal calculateBasketAmount( final List<Product> products ) {
        return products.stream()
                .map( Product::getPrice )
                .reduce( BigDecimal.ZERO, BigDecimal::add );
    }

}
