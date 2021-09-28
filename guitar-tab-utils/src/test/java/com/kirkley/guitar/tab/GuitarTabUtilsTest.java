package com.kirkley.guitar.tab;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GuitarTabUtilsTest {

	// private File badTabFile = null;
	private File goodTabFile = null;

	@Before
	public void setup() throws Exception {
//        badTabFile = new File(getClass().getResource("/bad.gp5").toURI());
		goodTabFile = new File("C:\\Users\\mattk\\src\\tmp\\Rammstein - Reise Reise (ver 6 by herbert.martini).gp");
	}

	@Test
	public void shouldDostuff() throws IOException {
		GuitarTabUtils.readTabMetaData(goodTabFile);
	}

//    @Test
//    public void saveShouldCreateFileIfItDoesNotExist() throws Exception{
//        GuitarTab tab = getGoodGuitarTab();
//        File outputFile = randomFile();
//        
//        assertFalse(outputFile.exists());
//        GuitarTabUtils.save(tab, outputFile);
//        assertTrue(outputFile.exists());
//    }
//    
//    @Test
//    public void saveShouldUpdateFileAppropriately() throws Exception {
//        GuitarTab tab = getGoodGuitarTab();
//        File outputFile = randomFile();
//        
//        String newAlbumName = randomString();
//        tab.setAlbum(newAlbumName);
//        GuitarTabUtils.save(tab, outputFile);
//        
//        GuitarTab savedTab = GuitarTabUtils.readTab(outputFile);
//        assertEquals(newAlbumName,savedTab.getAlbum());
//    }

//    @Test
//    public void testExportToAscii_toString() throws Exception {
//        System.out.println(GuitarTabUtils.exportToAscii(GuitarTabUtils
//                .readTab(goodTabFile)));
//    }

//    @Test
//    public void testReadTabMetaData() throws Exception {
//        TabMetaData data = GuitarTabUtils.readTabMetaData(badTabFile);
//        System.out.println(data);
//    }

	private String randomString() {
		return UUID.randomUUID().toString();
	}

	private GuitarTab getGoodGuitarTab() throws Exception {
		return GuitarTabUtils.readTab(goodTabFile);
	}

	private File randomFile() {
		String filename = randomString();
		String folder = System.getProperty("java.io.tmpdir");
		File returnFile = new File(folder, filename + ".gp5");
		returnFile.deleteOnExit();
		return returnFile;
	}
}
