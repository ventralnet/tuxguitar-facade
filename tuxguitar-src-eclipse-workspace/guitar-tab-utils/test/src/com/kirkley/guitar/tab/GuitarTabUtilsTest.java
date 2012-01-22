package com.kirkley.guitar.tab;

import java.io.File;

import junit.framework.TestCase;

public class GuitarTabUtilsTest extends TestCase {
   public void testReadTabMetaData() throws Exception {
	   File f = new File("./test.gp5");
	   TabMetaData data = GuitarTabUtils.readTabMetaData(f);
	   System.out.println (data);
   }
}