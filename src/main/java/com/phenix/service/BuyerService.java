package com.phenix.service;

import com.phenix.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);
}
