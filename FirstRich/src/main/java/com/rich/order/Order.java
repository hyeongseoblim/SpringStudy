package com.rich.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long accountId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice(){
     return itemPrice - discountPrice;
    }

}
