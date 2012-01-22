package com.kirkley.guitar.tab;

import java.io.File;

import junit.framework.TestCase;

public class GuitarTabUtilsTest extends TestCase {
   public void testReadTabMetaData() throws Exception {
	   File f = new File("./test.gp5");
	   TabMetaData data = GuitarTabUtils.readTabMetaData(f);
	   System.out.println (data);
   }
   
   public void testExporttoAscii() throws Exception{
	   File d = new File("./out.txt");
	   GuitarTab t = GuitarTabUtils.readTab(new File("./test.gp5"));
	   GuitarTabUtils.exportToAscii(t,d);
   }
}
