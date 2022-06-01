package com.rich.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonProtoTypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        ProtoTypeBean singletonBean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean singletonBean2 = ac.getBean(ProtoTypeBean.class);
        System.out.println("ProtoTypeBean1 Bean  = " + singletonBean1);
        System.out.println("ProtoTypeBean2 Bean  = " + singletonBean2);

        assertThat(singletonBean1).isNotSameAs(singletonBean2);

        ac.close();
    }
    @Test
    void singletonClientUseProtoType(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,ProtoTypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private ObjectProvider<ProtoTypeBean> protoTypeBeans;

        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeans.getObject();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        private int count = 0;

        @PostConstruct
        public void init() {
            System.out.println("ProtoType Bean  = init" + this);
        }
        @PreDestroy
        public void destory() {
            System.out.println("ProtoType Bean  = close");
        }

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
