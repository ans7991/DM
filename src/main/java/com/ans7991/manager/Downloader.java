package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import java.io.IOException;

public class Downloader {
  private Job job;
  private Thread thread;

  public Downloader(HttpFile httpFile, Progress progress) throws IOException {
    this.job = new Job(httpFile, progress);
  }

  public void start() throws InterruptedException {
    thread = new Thread(job);
    thread.start();
    job.startProgress();
  }

  public void pause() {
    thread.stop();
    job.stopProgress();
  }

  public void resume(HttpFile httpFile, Progress progress) throws IOException, InterruptedException {
    this.job = new Job(httpFile, progress);
    start();
  }
}
