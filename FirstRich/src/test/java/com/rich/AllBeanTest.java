package com.rich;


import com.rich.account.Account;
import com.rich.account.AccountGrade;
import com.rich.discount.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Account account = new Account(1L,"userA", AccountGrade.VIP);

        int discountPrice = discountService.discount(account,10000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(9000);
        int ratediscountPrice = discountService.discount(account,20000,"rateDiscountPolicy");

        assertThat(ratediscountPrice).isEqualTo(18000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> discountPolicies) {
            this.policyList = discountPolicies;
            this.policyMap = policyMap;
        }

        public int discount(Account account, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(account,price);
        }
    }


}
