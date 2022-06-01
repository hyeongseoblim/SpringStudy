package com.rich.beandefinition;

import com.rich.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinition {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


}
