package com.ans7991.manager;

public class Progress {
  private int percentCompleted;
  private Thread thread;

  void update(long downloaded, long total) {
    percentCompleted = (int) (downloaded * 100 / total);
  }

  void display() throws InterruptedException {
    thread = new Thread() {
      @Override
      public void run() {
        do {
          System.out.print("\rDownloading..............................." + percentCompleted + "%");
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } while (true);
      }
    };
    thread.start();
  }

  void stop() {
    thread.stop();
  }

  int getPercentCompleted() {
    return percentCompleted;
  }
}
