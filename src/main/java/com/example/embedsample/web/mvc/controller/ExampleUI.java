/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.example.embedsample.web.mvc.SimpleExample, created on 01/12/2015 15:46 <p>
 * @author Charles
 */
@Controller
public class ExampleUI {

  @RequestMapping("/*")
  public String index() {
    return "main-web-ui";
  }

}
