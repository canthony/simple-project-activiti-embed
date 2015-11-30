/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample;

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
}
