package com.sumanasaha.checkoutservice.bo;


import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Product {

    private final String id;
    private final String name;

    private final BigDecimal price;
    private final List<String> offers;

}
