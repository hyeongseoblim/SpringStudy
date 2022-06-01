package com.rich;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.rich",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
)
public class AutoAppConfig {


}
