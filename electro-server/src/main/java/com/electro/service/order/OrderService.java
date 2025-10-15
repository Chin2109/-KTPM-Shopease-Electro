package com.electro.service.order;

import com.electro.dto.client.ClientConfirmedOrderResponse;
import com.electro.dto.client.ClientSimpleOrderRequest;

public interface OrderService {
    ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request);
}
