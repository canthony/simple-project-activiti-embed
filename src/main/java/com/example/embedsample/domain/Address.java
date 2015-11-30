/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.domain;

/**
 * com.example.embedsample.domain.Address, created on 27/11/2015 15:22 <p>
 * @author Charles
 */
public class Address {
  protected String streetNumber;
  protected String street;
  protected String city;
  protected String postalCode;
  protected String country;

  private Address(Builder builder) {
    this.streetNumber = builder.streetNumber;
    this.street = builder.street;
    this.city = builder.city;
    this.postalCode = builder.postalCode;
    this.country = builder.country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public static class Builder {
    private String streetNumber;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public Builder streetNumber(String streetNumber) {
      this.streetNumber = streetNumber;
      return this;
    }

    public Builder street(String street) {
      this.street = street;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder postalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Builder fromPrototype(Address prototype) {
      streetNumber = prototype.streetNumber;
      street = prototype.street;
      city = prototype.city;
      postalCode = prototype.postalCode;
      country = prototype.country;
      return this;
    }

    public Address build() {return new Address(this);}
  }
}
