package com.ans7991.manager;

import org.junit.Assert;
import org.junit.Test;

public class ProgressTest {

  @Test
  public void shouldDisplayUpdatingProgress() throws Exception {
    Progress progress = new Progress();
    progress.display();
    Assert.assertEquals(0, progress.getProgress());

    progress.update(500, 1000);
    Assert.assertEquals(50, progress.getProgress());

    progress.stop();
  }
}