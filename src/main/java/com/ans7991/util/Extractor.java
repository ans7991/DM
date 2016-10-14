package com.ans7991.util;

import java.net.MalformedURLException;
import java.net.URL;

public class Extractor {
  public static String extractTarget(String[] args) throws MalformedURLException {
    String path = extractURL(args).getPath();
    return args[1].concat(path.substring(path.lastIndexOf('/')));
  }

  public static URL extractURL(String[] args) throws MalformedURLException {
    return new URL(args[0]);
  }
}
