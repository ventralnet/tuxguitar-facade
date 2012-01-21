package com.kirkley.guitar.tab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.herac.tuxguitar.gui.TuxGuitar;
import org.herac.tuxguitar.gui.util.MessageDialog;
import org.herac.tuxguitar.io.base.TGFileFormatException;
import org.herac.tuxguitar.io.base.TGFileFormatManager;
import org.herac.tuxguitar.io.base.TGInputStreamBase;
import org.herac.tuxguitar.io.gtp.GP1InputStream;
import org.herac.tuxguitar.io.gtp.GP2InputStream;
import org.herac.tuxguitar.io.gtp.GP3InputStream;
import org.herac.tuxguitar.io.gtp.GP4InputStream;
import org.herac.tuxguitar.io.gtp.GP5InputStream;
import org.herac.tuxguitar.io.gtp.GTPSettingsUtil;
import org.herac.tuxguitar.io.ptb.PTInputStream;
import org.herac.tuxguitar.song.models.TGSong;

public class GuitarTabUtils {


	
    private static TGFileFormatManager fileFormatManager = TGFileFormatManager.instance();
	
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
		
	}
	
	public static void main(String[] args) {
		try {
			InputStream stream = new File("/home/matt/test.gp3").toURI().toURL().openStream();
			TGSong song = TGFileFormatManager.instance().getLoader().load(TuxGuitar.instance().getSongManager().getFactory(),stream);
			System.out.println(new TabMetaData(song));
		}catch (Throwable throwable) {
			new TGFileFormatException(TuxGuitar.getProperty("file.open.error", new String[]{"url"}),throwable);
		}
	}
	
	public static TabMetaData readTabMetaData(File tab) throws FileNotFoundException {
		return readTabMetaData(new FileInputStream(tab));
	}
	
	public static TabMetaData readTabMetaData(InputStream stream) {
		return null;
		
	}
	
	
	
//	private static InputStream getInputStream(InputStream in)throws Throwable {
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		int read = 0;
//		while((read = in.read()) != -1){
//			out.write(read);
//		}
//		byte[] bytes = out.toByteArray();
//		in.close();
//		out.close();
//		out.flush();
//		return new ByteArrayInputStream(bytes);
//	}
}
