/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.domain;

import com.google.common.base.MoreObjects;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Date;

/**
 * Domain class for a Company
 *
 * @author Charles
 */
public class Company {

  private String registrationNumber;
  private String companyName;
  private String activityCode;
  private String activityDescription;
  private String statusCode;
  private String statusDescription;
  private Date registrationDate;

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

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

  public String getActivityCode() {
    return activityCode;
  }

  public void setActivityCode(String activityCode) {
    this.activityCode = activityCode;
  }

  public String getActivityDescription() {
    return activityDescription;
  }

  public void setActivityDescription(String activityDescription) {
    this.activityDescription = activityDescription;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  public Integer getAge() {
    return registrationDate == null ? null :
        Years.yearsBetween(new LocalDate(registrationDate), new LocalDate()).getYears();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("companyName", companyName)
        .add("registrationNumber", registrationNumber)
        .toString();
  }
}
