/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.service;

import com.example.embedsample.domain.Company;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import javax.annotation.PostConstruct;
import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * com.example.embedsample.CompanyRepository, created on 27/11/2015 16:23 <p>
 * @author Charles
 */
@Component
public class CompanyRepository implements ResourceLoaderAware {
  private static Logger LOGGER = LoggerFactory.getLogger(CompanyRepository.class);

  protected String resourceName = "classpath:companies.csv";
  protected Map<String, Company> regKeyToCompany = Maps.newHashMap();
  protected ResourceLoader resourceLoader;


  public CompanyRepository() {

  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @PostConstruct
  public void init() throws IOException {
    loadCompaniesFromCSV(resourceName);
  }

  /**
   * Loads companies from a CSV file
   *
   * @param resourceName
   * @throws IOException
   */
  protected void loadCompaniesFromCSV(String resourceName) throws IOException {
    Resource resource = resourceLoader.getResource(resourceName);

    try (CsvBeanReader reader = new CsvBeanReader(
        new InputStreamReader(resource.getInputStream()),
        CsvPreference.STANDARD_PREFERENCE)
    ) {

      /* Set the property names from the first line : trim and make the first character
       * lower case */
      String[] header = reader.getHeader(true);
      for (int i = 0; i < header.length; i++) {
        header[i] = Introspector.decapitalize(header[i].trim());
      }

      /* Setup up the column processors */
      CellProcessor[] processors = {new NotNull(new Trim()),
                                    new Trim()
      };

      /* Read the companies */
      for (Company company = reader.read(Company.class, header, processors);
           company != null;
           company = reader.read(Company.class, header, processors)) {
        addCompany(company);
      }

      if (LOGGER.isDebugEnabled()) {
        String asTest = Joiner.on(",").withKeyValueSeparator("=").join(regKeyToCompany);
        LOGGER.debug("loadCompaniesFromCSV - {}", asTest);
      }
    }

  }

  /**
   * Finds the company by the registration number
   * @param registrationNumber
   * @return the company, or null if it couldn't be found
   */
  public Company getCompanyByRegistrationNumber(String registrationNumber) {
    return regKeyToCompany.get(registrationNumber);
  }


  /**
   * Returns all of the companies, in natural order
   * @return
   */
  public Iterable<Company> getAllCompanies() {
    return ImmutableList.copyOf(regKeyToCompany.values());
  }

  /**
   * Adds a company
   * @param company Can't be null
   */
  protected void addCompany(Company company) {
    Preconditions.checkNotNull(company, "company cannot be null");
    regKeyToCompany.put(company.getRegistrationNumber(), company);
  }
}
