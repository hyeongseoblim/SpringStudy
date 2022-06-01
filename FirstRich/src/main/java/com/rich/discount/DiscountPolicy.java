package com.rich.discount;

import com.rich.account.Account;

public interface DiscountPolicy {
    /**
    * @return 할인 정책
    * */
    int discount(Account account, int price);
}
