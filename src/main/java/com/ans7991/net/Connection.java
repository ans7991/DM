package com.ans7991.net;

import com.ans7991.model.HttpFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Connection {
  private HttpURLConnection httpURLConnection;

  public Connection(HttpFile httpFile) throws IOException {
    httpURLConnection = (HttpURLConnection) httpFile.getUrl().openConnection();
    httpURLConnection.setRequestProperty("Range", "bytes=" + new File(httpFile.getTarget()).length() + "-");
  }

  public InputStream getInputStream() throws IOException {
    return httpURLConnection.getInputStream();
  }

  public int getContentLength() {
    return httpURLConnection.getContentLength();
  }

  public void disconnect() {
    httpURLConnection.disconnect();
  }
}
