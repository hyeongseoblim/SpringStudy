package com.rich.discount;

import com.rich.account.Account;
import com.rich.account.AccountGrade;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {
    // vip 일 경우 1000원 할인.
    private final int discountFixAmount = 1000;

    @Override
    public int discount(Account account, int price) {
        if (account.getGrade() != null && account.getGrade() != AccountGrade.GENERAL) {
            return price - discountFixAmount;
        }

        return price;
    }
}
