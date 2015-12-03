/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.service;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * com.example.embedsample.service.AccountsUploadService, created on 03/12/2015 12:38 <p>
 * @author Charles
 */
@Service
public class AccountsUploadService {
  public static String MESSAGE_ACCOUNTS_UPLOADED = "AccountsUploaded";
  private static Logger LOGGER = LoggerFactory.getLogger(AccountsUploadService.class);

// TODO : Demonstrate talking to Activiti Engine
//  protected RuntimeService activitiRuntimeservice;
//
//  @Autowired
//  public AccountsUploadService(RuntimeService runtimeService) {
//    this.activitiRuntimeService = runtimeService;
//  }

  /**
   * Temporary no-args constructor
   */
  public AccountsUploadService() {
    LOGGER.info("AccountsUploadService - Created");
  }


  public void accountsUploaded(String executionId, File accountsFile) {
    LOGGER.info("accountFilesUploaded - Received file {} for Execution {}", accountsFile, executionId);

    Map<String, Object> vars = ImmutableMap.<String, Object>of("accountsFile", accountsFile);

// TODO : Pass file on to Activiti
//    activitiRuntimeService.messageEventReceived(MESSAGE_ACCOUNTS_UPLOADED, executionId, vars);
  }
}
