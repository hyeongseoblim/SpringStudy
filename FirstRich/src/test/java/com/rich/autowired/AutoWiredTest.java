package com.rich.autowired;

import com.rich.account.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    @DisplayName("Autowired 옵션 테스트")
    void AutowiredOption(){
        ApplicationContext ac =  new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Account account){
            System.out.println("noAccount1 = "+ account);
        }
        @Autowired
        public void setNoBean2(@Nullable Account account){
            System.out.println("noAccount2 = "+ account);
        }
        @Autowired
        public void setNoBean3(Optional<Account> account){
            System.out.println("noAccount3 = "+ account);
        }
    }
}
