/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.rest;

import com.example.embedsample.domain.Company;
import com.example.embedsample.domain.CompanyOrdering;
import com.example.embedsample.service.CompanyService;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Rest API Controller, providing access to Company data. <p/>
 *
 * A simple front to {@link CompanyService}
 *
 * @author Charles
 */
@RestController
@RequestMapping("companies")
public class CompaniesAPIController {

  protected CompanyOrdering companyOrdering = new CompanyOrdering();
  protected CompanyService companyService;

  @Autowired
  public CompaniesAPIController(CompanyService companyService) {
    this.companyService = companyService;
  }

  /**
   * Search for companies
   *
   * @param sort
   * @return
   */
  @RequestMapping()
  public Iterable<CompanySearchResult> getCompanies(
      @RequestParam(defaultValue = "+companyName") String sort
  ) {

    // Create a sorted collection of companies, ordered by the sort provided in the
    // parameter
    ImmutableSortedSet<Company> sortedCompanies = FluentIterable
        .from(companyService.getAllCompanies())
        .toSortedSet(companyOrdering.getOrdering(sort));

    // Transform the Company in a Search Result
    return ImmutableSet.copyOf(Iterables.transform(sortedCompanies, new Function<Company, CompanySearchResult>() {
      @Override
      public CompanySearchResult apply(Company input) {
        return new CompanySearchResult(input.getRegistrationNumber(), input.getCompanyName());
      }
    }));
  }

  /**
   * Get the given company
   *
   * @param regKey The registration number of the company
   * @return
   */
  @RequestMapping("/{regKey}")
  public Company getCompany(@PathVariable("regKey") String regKey) {
    Company company = companyService.getCompanyByRegistrationNumber(regKey);
    if (company == null) {
      throw new CompanyNotFoundException();
    }
    return company;
  }


  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company Not Found")
  protected static class CompanyNotFoundException extends RuntimeException {
  }

}
