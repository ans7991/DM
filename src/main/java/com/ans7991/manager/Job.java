package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import com.ans7991.net.Connection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Job {
  private static final int BUFFER_SIZE = 8192;
  private Thread thread;

  private HttpFile httpFile;
  private Progress progress;
  private Connection connection;

  public Job(HttpFile httpFile) throws IOException {
    this.httpFile = httpFile;
    this.progress = new Progress();
    this.connection = new Connection(httpFile);
  }

  public void start() throws InterruptedException {
    thread = new Thread() {
      @Override
      public void run() {
        try {
          saveFile();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          connection.disconnect();
          progress.stop();
        }
      }
    };
    thread.start();
    progress.display();
  }

  public void stop() {
    thread.stop();
    progress.stop();
  }

  private void saveFile() throws IOException {
    long downloadedSize = httpFile.getDownloadedCopyLength();
    long totalSize = downloadedSize + connection.getContentLength();

    final FileOutputStream outputStream = new FileOutputStream(httpFile.getTarget(), true);
    final InputStream inputStream = connection.getInputStream();

    saveFileAndUpdateProgress(downloadedSize, totalSize, outputStream, inputStream);
  }

  private void saveFileAndUpdateProgress(long downloadedSize, long totalSize, FileOutputStream outputStream, InputStream inputStream) throws IOException {
    final byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
      downloadedSize += bytesRead;
      progress.update(downloadedSize, totalSize);
    }
  }

  public boolean isRunning() {
    return thread.isAlive();
  }
}
