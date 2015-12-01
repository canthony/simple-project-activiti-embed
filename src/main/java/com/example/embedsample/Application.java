/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample;

import com.example.embedsample.web.rest.RestConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * com.example.embedsample.Application, created on 27/11/2015 14:31 <p>
 * @author Charles
 */
@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
@ComponentScan(excludeFilters =
@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.embedsample.web.*")
)
public class Application {
  private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
  }


  @Bean
  public ServletRegistrationBean restApiDispatcher() {
    return createServletRegistrationBean(RestConfiguration.class,
        "restApiDispatcher",
        "/special-api/*");
  }

  protected ServletRegistrationBean createServletRegistrationBean(Class<?> configClass, String name, String... mappings) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(configClass);
    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet, mappings);

    bean.setName(name);
    bean.setLoadOnStartup(1);
    return bean;
  }

}
