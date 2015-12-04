/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.domain;

import com.example.embedsample.util.OrderingFactory;
import com.google.common.collect.Ordering;

/**
 * Provides named Ordering for the Company domain object
 *
 * @author Charles
 */
public class CompanyOrdering extends OrderingFactory<Company> {

  public CompanyOrdering() {
    addOrdering("companyName", new Ordering<Company>() {
      @Override
      public int compare(Company left, Company right) {
        return Ordering.natural().compare(left.getCompanyName(), right.getCompanyName());
      }
    });
    addOrdering("registrationNumber", new Ordering<Company>() {
      @Override
      public int compare(Company left, Company right) {
        return Ordering.natural().compare(left.getRegistrationNumber(), right.getRegistrationNumber());
      }
    });
    setDefaultName("companyName");
  }

}
