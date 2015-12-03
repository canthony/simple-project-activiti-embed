/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * com.example.embedsample.WebMvcConfiguration, created on 01/12/2015 14:12 <p>
 * @author Charles
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class WebUIConfig {
  private static Logger LOGGER = LoggerFactory.getLogger(WebUIConfig.class);

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    return new CommonsMultipartResolver();
  }

}
