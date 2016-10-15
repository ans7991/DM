package com.ans7991.integration;

import com.ans7991.manager.Downloader;
import com.ans7991.model.HttpFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class DownloadManagerIT {
  private Downloader downloader;
  private HttpFile httpFile;
  private String targetPath;
  private File downloadedFile;
  private Path path;

  @Before
  public void setUp() throws Exception {
    URL url = new URL("https://wiki.sugarlabs.org/images/7/74/Ubuntu-small.jpg");
    path = Files.createTempDirectory("temp-download");
    targetPath = path.toAbsolutePath().toString().concat("Ubuntu-small.jpg");
    httpFile = new HttpFile(url, targetPath);

    downloader = new Downloader(httpFile);
  }

  @Test
  public void shouldBeAbleToDownloadAFile() throws Exception {
    downloadedFile = new File(targetPath);
    assertFalse(downloadedFile.exists());

    downloader.start();
    Thread.sleep(5000);

    assertTrue(downloadedFile.exists());
    assertEquals(3023, downloadedFile.length());
  }

  @Test
  public void shouldBeAbleToPauseAndResumeAFileDownload() throws Exception {
    downloadedFile = new File(targetPath);
    assertFalse(downloadedFile.exists());

    downloader.start();
    Thread.sleep(1000);

    downloader.pause();
    Thread.sleep(1000);

    downloader.resume(httpFile);
    Thread.sleep(4000);

    assertTrue(downloadedFile.exists());
    assertEquals(3023, downloadedFile.length());
  }

  @After
  public void tearDown() throws Exception {
    downloadedFile.delete();
    path.toFile().delete();
  }
}
