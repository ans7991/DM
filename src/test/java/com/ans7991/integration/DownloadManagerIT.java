package com.ans7991.integration;

import com.ans7991.manager.Downloader;
import com.ans7991.manager.Progress;
import com.ans7991.model.HttpFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

public class DownloadManagerIT {
  private Downloader downloader;
  private HttpFile httpFile;
  private Progress progress;
  private String targetPath;
  private File downloadedFile;

  @Before
  public void setUp() throws Exception {
    URL url = new URL("https://wiki.sugarlabs.org/images/7/74/Ubuntu-small.jpg");
    targetPath = "/Users/anshulrastogi/Downloads/temp/Ubuntu-small.jpg";
    httpFile = new HttpFile(url, targetPath);

    progress = new Progress();
    downloader = new Downloader(httpFile, progress);
  }

  @Test
  public void shouldBeAbleToDownloadAFile() throws Exception {
    downloadedFile = new File(targetPath);
    assertFalse(downloadedFile.exists());

    downloader.start();
    Thread.sleep(3000);

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

    downloader.resume(httpFile, progress);
    Thread.sleep(4000);

    assertTrue(downloadedFile.exists());
    assertEquals(3023, downloadedFile.length());
  }

  @After
  public void tearDown() throws Exception {
    downloadedFile.delete();
  }
}
