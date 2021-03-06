package com.kirkley.guitar.tab;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.herac.tuxguitar.gui.editors.tab.TGFactoryImpl;
import org.herac.tuxguitar.io.ascii.ASCIISongExporter;
import org.herac.tuxguitar.io.base.TGFileFormatException;
import org.herac.tuxguitar.io.base.TGFileFormatManager;
import org.herac.tuxguitar.io.gtp.GP1InputStream;
import org.herac.tuxguitar.io.gtp.GP2InputStream;
import org.herac.tuxguitar.io.gtp.GP3InputStream;
import org.herac.tuxguitar.io.gtp.GP3OutputStream;
import org.herac.tuxguitar.io.gtp.GP4InputStream;
import org.herac.tuxguitar.io.gtp.GP4OutputStream;
import org.herac.tuxguitar.io.gtp.GP5InputStream;
import org.herac.tuxguitar.io.gtp.GP5OutputStream;
import org.herac.tuxguitar.io.gtp.GTPSettingsUtil;
import org.herac.tuxguitar.io.ptb.PTInputStream;
import org.herac.tuxguitar.song.managers.TGSongManager;
import org.herac.tuxguitar.song.models.TGSong;

public class GuitarTabUtils {


	
    private static TGFileFormatManager fileFormatManager = TGFileFormatManager.instance();
	
    private static TGSongManager songManager;
    
	static {
		/*
		 * Init all the file formats
		 */
		GP1InputStream gp1InputStream = new GP1InputStream(GTPSettingsUtil.instance().getSettings());
		GP2InputStream gp2InputStream = new GP2InputStream(GTPSettingsUtil.instance().getSettings());
		GP3InputStream gp3InputStream = new GP3InputStream(GTPSettingsUtil.instance().getSettings());
		GP4InputStream gp4InputStream = new GP4InputStream(GTPSettingsUtil.instance().getSettings());
		GP5InputStream gp5InputStream = new GP5InputStream(GTPSettingsUtil.instance().getSettings());
		
		PTInputStream ptInputStream = new PTInputStream();
		
		fileFormatManager.addInputStream(gp1InputStream);
		fileFormatManager.addInputStream(gp2InputStream);
		fileFormatManager.addInputStream(gp3InputStream);
		fileFormatManager.addInputStream(gp4InputStream);
		fileFormatManager.addInputStream(gp5InputStream);
		fileFormatManager.addInputStream(ptInputStream);
		
		GP3OutputStream gp3OutputStream = new GP3OutputStream(GTPSettingsUtil.instance().getSettings());
		GP4OutputStream gp4OutputStream = new GP4OutputStream(GTPSettingsUtil.instance().getSettings());
		GP5OutputStream gp5OutputStream = new GP5OutputStream(GTPSettingsUtil.instance().getSettings());
		
		fileFormatManager.addOutputStream(gp3OutputStream);
		fileFormatManager.addOutputStream(gp4OutputStream);
		fileFormatManager.addOutputStream(gp5OutputStream);
		
	}
	
	public static void exportToAscii(GuitarTab tab, File destination) throws IOException {
		String textVersion = exportToAscii(tab);
		BufferedWriter out = new BufferedWriter(new FileWriter(destination));
		out.write(textVersion);
		out.close();
	}
	
	public static void exportToAscii(File tab, File destination) throws IOException {
		exportToAscii(readTab(tab),destination);
	}
	
	public static String exportToAscii(GuitarTab tab) {
		ASCIISongExporter exporter = new ASCIISongExporter();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try{
		exporter.init(new CustomFactoryImpl(),stream);
		exporter.exportSong(tab);
		return new String(stream.toByteArray());
		} finally { try{stream.close();}catch(Exception e) {} }
	}
	
	public static void save(final GuitarTab tab, final File outputFile) throws IOException, TGFileFormatException {
	    fileFormatManager.getWriter().write(getSongManager(tab).getFactory(), tab, outputFile.getAbsolutePath());
	}
	
	public static TabMetaData readTabMetaData(File tab) throws IOException {
		return readTabMetaData(new FileInputStream(tab));
	}
	
	public static TabMetaData readTabMetaData(InputStream stream) throws IOException {
	    return readTab(stream).getTabMetaData();
	}
	
	public static GuitarTab readTab(File tab) throws IOException{
		try {
			return readTab(new FileInputStream(tab));
		} catch (Exception e) {
			throw new IOException("Unable the read file: "
                           + tab == null ? "null" : tab.getAbsolutePath(),e);
		}
	}
	
	public static GuitarTab readTab(InputStream stream) throws IOException{
	    try{
    		GuitarTab song = (GuitarTab)TGFileFormatManager.instance().getLoader().load(getSongManager(new GuitarTab()).getFactory(),stream);  
    		return song;
	    } catch (Exception e) {
	    	throw new IOException("Unable to read tab", e);
	    } finally {
	    	try{ stream.close(); } catch(Exception e){}
	    }
	}
	
	private static TGSongManager getSongManager(TGSong song) {
		if(songManager == null){
			songManager = new TGSongManager(new CustomFactoryImpl());
			songManager.setSong(song);
		}
		return songManager;
	}
	
	private static class CustomFactoryImpl extends TGFactoryImpl {
		@Override
		public TGSong newSong() {
			return new GuitarTab();
		}
	}
}
