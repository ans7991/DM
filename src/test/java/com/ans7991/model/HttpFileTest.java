package com.ans7991.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

public class HttpFileTest {
  @Test
  public void shouldGetLengthOfDownloadedFileAsZeroIfFileNotExists() throws Exception {
    HttpFile httpFile = new HttpFile(new URL("http:/url.xyz"), "abc.txt");
    long localCopyLength = httpFile.getDownloadedCopyLength();

    Assert.assertEquals(0, localCopyLength);
  }

  @Test
  public void shouldGetLengthOfDownloadedFile() throws Exception {
    File tempFile = File.createTempFile("temp", "txt");
    tempFile.deleteOnExit();

    Files.write(tempFile.toPath(), "This is the temporary file content".getBytes());

    HttpFile httpFile = new HttpFile(new URL("http:/url.xyz"), tempFile.getPath());
    long localCopyLength = httpFile.getDownloadedCopyLength();

    Assert.assertEquals(34, localCopyLength);
  }

}