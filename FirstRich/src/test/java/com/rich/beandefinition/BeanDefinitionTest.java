package com.rich.beandefinition;

import com.rich.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionName = ac.getBeanDefinitionNames();

        for (String beanName: beanDefinitionName         ) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanName"+beanDefinitionName + " beanDefinition" + beanDefinition);
            }
        }
    }

}