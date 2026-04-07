package com.electro.service.inventory;

import com.electro.dto.product.SerialResponse;
import com.electro.entity.order.Order;

import java.util.List;

public interface SerialService {

    List<SerialResponse> getByVariantId(Long variantId);
    void assignSerialToOrder(Order order);
    List<SerialResponse> getByOrderId(Long orderId);
}