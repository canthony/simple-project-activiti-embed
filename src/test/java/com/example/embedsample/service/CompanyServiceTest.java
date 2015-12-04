package com.example.embedsample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * com.example.embedsample.CompanyRepositoryTest, created on 30/11/2015 09:01 <p>
 * @author Charles
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CompanyServiceTest.Config.class)
public class CompanyServiceTest {
  @Autowired
  protected ResourceLoader load;


  @Test
  public void testInit() throws IOException {
    CompanyService repository = new CompanyService();
    repository.resourceLoader = load;

    repository.init();
  }


  @Configuration
  public static class Config {

  }
}