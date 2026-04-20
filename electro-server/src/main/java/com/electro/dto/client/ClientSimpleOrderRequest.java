package com.electro.dto.client;

import com.electro.entity.cashbook.PaymentMethodType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientSimpleOrderRequest {
    private PaymentMethodType paymentMethodType;
    private BigDecimal discountPercent;
}
