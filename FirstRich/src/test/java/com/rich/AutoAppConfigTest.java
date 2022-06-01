package com.rich;


import com.rich.Filter.BeanA;
import com.rich.Filter.BeanB;
import com.rich.Filter.MyEncludeComponent;
import com.rich.Filter.MyExcludeComponent;
import com.rich.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AutoAppConfigTest {

    @Test
    void basicBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        AccountService accountService = ac.getBean(AccountService.class);
        assertThat(accountService).isInstanceOf(AccountService.class);
    }

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac =
        new AnnotationConfigApplicationContext(ComponentFilterConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
    classes = MyEncludeComponent.class),
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
    classes = MyExcludeComponent.class))
    static class ComponentFilterConfig{};
}
