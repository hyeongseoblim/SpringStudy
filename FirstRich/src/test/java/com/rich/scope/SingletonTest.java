package com.rich.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("SingleTone Bean  = "+singletonBean1);
        System.out.println("SingleTone Bean  = "+singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();

    }


    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingleTone Bean  = init");

        }
        @PreDestroy
        public void destory(){
            System.out.println("SingleTone Bean  = close");

        }
    }
}
