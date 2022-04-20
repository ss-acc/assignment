package com.sumanasaha.checkoutservice.bo;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Basket {

    @Size( max = 30 )
    private String basketId;

    private List<Product> products;

    @NotBlank
    @NotNull
    @Size( max = 20 )
    private String customerId;

    private LocalDateTime timeStamp;

    private BigDecimal basketAmount;

    private BasketStatus basketStatus;
}
