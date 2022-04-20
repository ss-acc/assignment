package com.sumanasaha.checkoutservice.service;


public interface CheckoutService {

    /**
     * Adds a new product to a Basket.
     * Returns nothing and saves the basket details in the in memory hashmap.
     *
     * @param customerId non null customer Id given via rest endpoint
     * @param productId non null product Id given via rest endpoint
     */
    String addProductToBasket( String customerId, String productId );


    /**
     * Removes an existing product from a Basket.
     * Returns nothing and saves the updated basket details in the in memory hashmap.
     *
     * @param customerId non null customer Id given via rest endpoint
     * @param productId non null product Id given via rest endpoint
     */
    String removeProductFromBasket( String customerId, String productId );
}
