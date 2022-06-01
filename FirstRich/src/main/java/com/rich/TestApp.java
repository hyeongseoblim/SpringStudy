package com.rich;

import com.rich.account.AccountService;
import com.rich.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService accountService = applicationContext.getBean("accountService",AccountService.class);
        OrderService orderService = applicationContext.getBean("orderService",OrderService.class);

    }
}
