package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import java.io.IOException;

public class Downloader {
  private Job job;

  public Downloader(HttpFile httpFile) throws IOException {
    this.job = new Job(httpFile);
  }

  public void start() throws InterruptedException {
    job.start();
  }

  public void pause() {
    job.stop();
  }

  public void resume(HttpFile httpFile) throws IOException, InterruptedException {
    this.job = new Job(httpFile);
    start();
  }
}
