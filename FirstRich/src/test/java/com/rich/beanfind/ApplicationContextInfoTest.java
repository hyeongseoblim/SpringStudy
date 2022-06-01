package com.rich.beanfind;

import com.rich.AppConfig;
import com.rich.account.AccountService;
import com.rich.account.AccountServiceImpl;
import com.rich.account.MemoryAccountRepository;
import com.rich.discount.DiscountPolicy;
import com.rich.discount.FixDiscountPolicy;
import com.rich.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("config 읽어오깅")
    void loadTest() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object bean = ac.getBean(beanName);
            System.out.println("name = " + beanName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("application bean name")
    void loadApplicationName() {
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
            //스프링에서 정의한게아닌 외부에서나 내가 애플리케이션을 개발하기 위해 정의한 빈을 가져오기 위한 것
            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanName);
                System.out.println("name = " + beanName + " object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("클래스 이름으로 조회")
    void findClassNameTest() {
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        assertThat(accountService).isInstanceOf(AccountService.class);
    }

    @Test
    @DisplayName("클래스 타입으로 조회")
    void findClassTypeTest() {
        AccountService accountService = ac.getBean(AccountService.class);
        assertThat(accountService).isInstanceOf(AccountService.class);
    }

    @Test
    @DisplayName("구체적인 타입으로 조회")
    void findSiTest() {
        AccountService accountService = ac.getBean(AccountServiceImpl.class);
        assertThat(accountService).isInstanceOf(AccountService.class);
    }

    @Test
    @DisplayName("같은 타입 존재시 중복 오류")
    void findSameTest() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac2.getBean(MemoryAccountRepository.class));
    }

    @Test
    @DisplayName("같은 타입이 존재할 경우는 이름을 지정해서 검색하면 된다.")
    void findSameCaseByName() {
        MemoryAccountRepository bean = ac2.getBean("accountRepository1", MemoryAccountRepository.class);
        assertThat(bean).isInstanceOf(MemoryAccountRepository.class);
    }

    @Test
    @DisplayName("특정타입 모두 조회하기")
    void findClassType() {
        Map<String, MemoryAccountRepository> beansOfType = ac2.getBeansOfType(MemoryAccountRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = ac2.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac2.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있을 시 빈 이름을 지정")
    void findBeanByChildName(){
        DiscountPolicy rateP = ac2.getBean("rate",DiscountPolicy.class);
        assertThat(rateP).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있을 시 구체적인 서브타입으로 검색")
    void findBeanBySubType(){
        DiscountPolicy rateP = ac2.getBean(RateDiscountPolicy.class);
        assertThat(rateP).isInstanceOf(RateDiscountPolicy.class);
    }


    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemoryAccountRepository accountRepository1() {
            return new MemoryAccountRepository();
        }

        @Bean
        public MemoryAccountRepository accountRepository2() {
            return new MemoryAccountRepository();
        }

        @Bean
        public DiscountPolicy rate() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fix() {
            return new FixDiscountPolicy();
        }
    }


}