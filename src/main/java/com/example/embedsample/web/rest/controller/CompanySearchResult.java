/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.rest.controller;

/**
 * com.example.embedsample.domain.CompanySearchResult, created on 01/12/2015 11:40 <p>
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