/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample;

import com.example.embedsample.web.mainui.WebUIConfig;
import com.example.embedsample.web.rest.RestConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.AbstractConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Main Entry point for the application.
 * <p/>
 * Also serves as the main configuration class; note that we are explicitly
 * excluding the web package, as we are registering two separate Spring Dispatcher
 * servlets at two separate URLs
 *
 * @author Charles
 */
@SpringBootApplication()
@ComponentScan(excludeFilters =
@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.embedsample.web.*")
)
public class Application {
  private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

  /**
   * Main Entry Point
   * @param args
   */
  public static void main(String[] args) {

    SpringApplication application = new SpringApplication(Application.class);
    ConfigurableApplicationContext context = application.run(args);
    AbstractConfigurableEmbeddedServletContainer bean = context.getBean(AbstractConfigurableEmbeddedServletContainer.class);

    String url = "http://locahost:8080";
    try {
      String hostName = InetAddress.getLocalHost().getHostName();
      int port = bean.getPort();
      url = String.format("http://%s:%s", hostName, port);
    } catch (UnknownHostException e) {
      LOGGER.warn("main - Error Occurred", e);
    }
    LOGGER.info("{}", url);
  }


  /**
   * Creates a servlet registration for the Spring Dispatcher servlet used to
   * serve controllers from com.example.embedsample.web.rest -
   * i.e. our "special" REST API Dispatcher
   *
   * @return
   */
  @Bean
  public ServletRegistrationBean restApiDispatcher() {
    return createDispatcherServlet(RestConfiguration.class,
        "restApiDispatcher",
        "/special-api/*");
  }


  /**
   * Creates a servlet registration for the Spring Dispatcher servlet used to
   * serve controllers from com.example.embedsample.web.mvc
   * i.e. our "Main" UI Dispatcher
   *
   * @return
   */
  @Bean
  public ServletRegistrationBean mainUiDispatcher() {
    return createDispatcherServlet(WebUIConfig.class,
        "mainUiDispatcher",
        "/main-ui/*");
  }


  /**
   * Creates a servlet registration bean for a Spring Dispatcher, using a given Spring Config class for the
   * application context.
   *
   * @param configClass  The configuration class for the Web Context used by the dispatcher
   * @param name The name of the dispatcher servlet instance
   * @param mappings The  url mapping for the dispatcher servlet
   * @return
   */
  protected ServletRegistrationBean createDispatcherServlet(Class<?> configClass, String name, String... mappings) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(configClass);
    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet, mappings);

    bean.setName(name);
    bean.setLoadOnStartup(1);
    return bean;
  }

}
