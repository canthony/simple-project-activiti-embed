/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.mainui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration for the Main UI portion of the application
 *
 * @author Charles
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class WebUIConfig {

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    return new CommonsMultipartResolver();
  }

}
