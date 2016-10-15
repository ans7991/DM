package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobTest {
  private Job job;
  private String targetPath;
  private HttpFile httpFile;
  private Path path;

  @Before
  public void setUp() throws Exception {
    URL url = new URL("https://wiki.sugarlabs.org/images/7/74/Ubuntu-small.jpg");
    path = Files.createTempDirectory("temp-download");
    targetPath = path.toAbsolutePath().toString().concat("Ubuntu-small.jpg");
    httpFile = new HttpFile(url, targetPath);

    job = new Job(httpFile);
  }

  @Test
  public void shouldStartAJob() throws Exception {
    job.start();
    assertTrue(job.isRunning());
    job.stop();
  }

  @Test
  public void shouldStopAJob() throws Exception {
    job.start();
    assertTrue(job.isRunning());
    job.stop();
    Thread.sleep(1);
    assertFalse(job.isRunning());
  }
}