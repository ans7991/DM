package com.ans7991;

import com.ans7991.manager.Downloader;
import com.ans7991.util.Extractor;
import com.ans7991.model.HttpFile;
import com.ans7991.manager.Progress;

import java.io.IOException;
import java.net.URL;

public class Driver {
  public static void main(String[] args) throws IOException, InterruptedException {
    if (args.length < 2) {
      System.out.println("Please provide file url to download, and target directory to save downloaded file!!");
      System.out.println("Usage: gradle download -Purl=<fileURL> -Plocation=<target_directory>");
      return;
    }
    URL url = Extractor.extractURL(args);
    String targetPath = Extractor.extractTarget(args);

    HttpFile httpFile = new HttpFile(url, targetPath);
    Progress progress = new Progress();
    Downloader downloader = new Downloader(httpFile, progress);

    downloader.start();
  }
}
