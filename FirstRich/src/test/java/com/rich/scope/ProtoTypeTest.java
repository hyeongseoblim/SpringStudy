package com.rich.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeTest {
    
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        ProtoTypeBean singletonBean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean singletonBean2 = ac.getBean(ProtoTypeBean.class);
        System.out.println("ProtoTypeBean1 Bean  = "+singletonBean1);
        System.out.println("ProtoTypeBean2 Bean  = "+singletonBean2);

        Assertions.assertThat(singletonBean1).isNotSameAs(singletonBean2);

        ac.close();
    }
    
    @Scope("prototype")
    static class ProtoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("ProtoType Bean  = init");

        }
        @PreDestroy
        public void destory(){
            System.out.println("ProtoType Bean  = close");

        }
    }
}
