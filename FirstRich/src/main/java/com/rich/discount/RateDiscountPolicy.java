package com.rich.discount;

import com.rich.account.Account;
import com.rich.account.AccountGrade;
import com.rich.annotation.MainDiscountPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(Account account, int price) {
        if(account.getGrade() != null && (account.getGrade() == AccountGrade.VVIP || account.getGrade() == AccountGrade.VIP)){
            return (int)(price*0.9);
        }
        return price;
    }
}
