package com.ans7991.util;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ExtractorTest {

  private String[] args;

  @Before
  public void setUp() throws Exception {
    args = new String[]{"https://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar", "/Users/anshulrastogi/Downloads/temp"};
  }

  @Test
  public void shouldExtractTargetPathFromArgs() throws Exception {
    String target = Extractor.extractTargetPath(args);
    assertEquals("/Users/anshulrastogi/Downloads/temp/postgresql-9.2-1002.jdbc4.jar", target);
  }

  @Test
  public void shouldExtractURLFromArgs() throws Exception {
    URL url = Extractor.extractURL(args);
    assertEquals(new URL("https://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar"), url);
  }

}