/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.web.mainui;

import com.example.embedsample.service.AccountsUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controller for the main UI of the application
 *
 * @author Charles
 */
@Controller
public class ExampleUI {

  private static Logger LOGGER = LoggerFactory.getLogger(ExampleUI.class);

  protected AccountsUploadService accountsUploadService;

  @Autowired
  public ExampleUI(AccountsUploadService accountsUploadService) {
    this.accountsUploadService = accountsUploadService;
  }

  /**
   * Show the main UI
   * @return
   */
  @RequestMapping("/*")
  public String index() {
    return "main-web-ui";
  }

  /**
   * Shows the UI for File Upload
   * @return
   */
  @RequestMapping(value = "/upload", method = RequestMethod.GET)
  public String upload() {
    return "file-upload";
  }

  /**
   * Handles the upload of a file, and passes it on to the {@link AccountsUploadService}
   *
   * @param executionId
   * @param uploadedFile
   * @return
   */
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String handleFileUpload(
      @RequestParam("execution-id") String executionId,
      @RequestParam("file") MultipartFile uploadedFile) {

    if (uploadedFile.isEmpty()) {
      LOGGER.info("handleFileUpload - File Empty");
    } else {
      try {
        Path tempDirectory = Files.createTempDirectory("upload");
        Path tempFilePath = Paths.get(tempDirectory.toString(), uploadedFile.getOriginalFilename());

        Files.copy(uploadedFile.getInputStream(), tempFilePath);
        accountsUploadService.accountsUploaded(executionId, tempFilePath.toFile());
        Files.delete(tempFilePath);
        Files.delete(tempDirectory);
      } catch (IOException e) {
        LOGGER.error("handleFileUpload - Error during file upload", e);
      }
    }
    return "file-upload-success";
  }


}
