package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobTest {
  private Job job;
  private Progress mockProgress;

  @Before
  public void setUp() throws Exception {
    mockProgress = mock(Progress.class);
    HttpFile httpFile = new HttpFile(new URL("http://url.xyz"), "");
    job = new Job(httpFile, mockProgress);
  }

  @Test
  public void shouldStopProgress() throws Exception {
    job.stopProgress();
    verify(mockProgress).stop();
  }

  @Test
  public void shouldStartProgress() throws Exception {
    job.startProgress();
    verify(mockProgress).display();
  }
}