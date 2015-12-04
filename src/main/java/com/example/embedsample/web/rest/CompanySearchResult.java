/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.rest;

/**
 * A Cut-Down version of the Company domain object, used as a result of a company search
 *
 * @author Charles
 */
public class CompanySearchResult {
  String registrationNumber;
  String companyName;

  public CompanySearchResult(String registrationNumber, String companyName) {
    this.registrationNumber = registrationNumber;
    this.companyName = companyName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }
}