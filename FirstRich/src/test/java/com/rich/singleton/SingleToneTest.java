package com.rich.singleton;

import com.rich.AppConfig;
import com.rich.account.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {

    @Test
    @DisplayName("스프링 x 순수 DI container")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 조회 호출 시 마다 객체 생성
        AccountService accountService = appConfig.accountService();
        //2. 조회 호출 시 마다 객체 생성
        AccountService accountService1 = appConfig.accountService();

        //참조가 다른 것을 확인
        Assertions.assertThat(accountService).isNotSameAs(accountService1);

    }
    @Test
    @DisplayName("싱글톤 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService).isSameAs(singletonService2);
    }
    @Test
    @DisplayName("싱글톤 컨테이너 적용한 객체 사용")
    void singletonContainerTest(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService singletonService= annotationConfigApplicationContext.getBean("accountService",AccountService.class);
        AccountService singletonService2= annotationConfigApplicationContext.getBean("accountService",AccountService.class);

    Assertions.assertThat(singletonService).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("싱글톤 컨텍스트 테스트")
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    }
}
