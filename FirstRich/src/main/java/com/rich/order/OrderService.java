package com.rich.order;

public interface OrderService {

    Order createOrder(Long accountId, String itemName, int itemPrice);

}
