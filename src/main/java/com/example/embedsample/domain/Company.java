/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.domain;

import com.google.common.base.MoreObjects;

/**
 * com.example.embedsample.domain.Company, created on 27/11/2015 15:21 <p>
 * @author Charles
 */
public class Company {

  private String registrationNumber;
  private String companyName;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("companyName", companyName)
        .add("registrationNumber", registrationNumber)
        .toString();
  }
}
