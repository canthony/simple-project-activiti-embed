/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * com.example.embedsample.Application, created on 27/11/2015 14:31 <p>
 * @author Charles
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
  }


  @Autowired(required = true)
  public void configure(ObjectMapper jackson2ObjectMapper) {
    jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }
}
