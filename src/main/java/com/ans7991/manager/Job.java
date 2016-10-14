package com.ans7991.manager;

import com.ans7991.model.HttpFile;
import com.ans7991.net.Connection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Job implements Runnable {

  private static final int BUFFER_SIZE = 8192;
  private HttpFile httpFile;
  private Progress progress;
  private Connection connection;

  public Job(HttpFile httpFile, Progress progress) throws IOException {
    this.httpFile = httpFile;
    this.progress = progress;
    this.connection = new Connection(httpFile);
  }

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

  private void saveFile() throws IOException {
    final byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    long downloadedSize = httpFile.getDownloadedCopyLength();
    long totalSize = downloadedSize + connection.getContentLength();

    final FileOutputStream outputStream = new FileOutputStream(httpFile.getTarget(), true);
    final InputStream inputStream = connection.getInputStream();

    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
      downloadedSize += bytesRead;
      progress.update(downloadedSize, totalSize);
    }
  }

  public void stopProgress() {
    progress.stop();
  }

  public void startProgress() throws InterruptedException {
    progress.display();
  }
}
