package com.ans7991.model;

import java.io.File;
import java.net.URL;

public class HttpFile {
  private URL url;
  private String target;

  public HttpFile(URL url, String target) {
    this.url = url;
    this.target = target;
  }

  public String getTarget() {
    return target;
  }

  public URL getUrl() {
    return url;
  }

  public long getDownloadedCopyLength() {
    return new File(target).length();
  }
}
