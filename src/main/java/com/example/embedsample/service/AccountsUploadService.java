/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.service;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.Map;

/**
 * An example service to illustrate a Spring bean talking to an Activiti process.
 *
 * Here, we are standing in for a user uploading a file that needs to add to a running
 * process, and the process notified that the fil has been uploaded.
 *
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

  @PreDestroy
  private void destroy() {
    LOGGER.info("Closed");
  }

  /**
   * Notify the given Activiti process that an accounts file has been uploaded
   *
   * @param executionId
   * @param accountsFile
   */
  public void accountsUploaded(String executionId, File accountsFile) {
    LOGGER.info("accountFilesUploaded - Received file {} for Execution {}", accountsFile, executionId);

    Map<String, Object> vars = ImmutableMap.<String, Object>of("accountsFile", accountsFile);

// TODO : Pass file on to Activiti
//    activitiRuntimeService.messageEventReceived(MESSAGE_ACCOUNTS_UPLOADED, executionId, vars);
  }
}
