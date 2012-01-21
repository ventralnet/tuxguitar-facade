package com.kirkley.guitar.tab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.herac.tuxguitar.io.base.TGInputStreamBase;
import org.herac.tuxguitar.io.gtp.GP1InputStream;
import org.herac.tuxguitar.io.gtp.GP2InputStream;
import org.herac.tuxguitar.io.gtp.GP3InputStream;
import org.herac.tuxguitar.io.gtp.GP4InputStream;
import org.herac.tuxguitar.io.gtp.GP5InputStream;
import org.herac.tuxguitar.io.gtp.GTPSettingsUtil;
import org.herac.tuxguitar.io.ptb.PTInputStream;

public class GuitarTabUtils {

	private static GP5InputStream gp5Stream = null;
	private static GP4InputStream gp4Stream = null;
	private static GP3InputStream gp3Stream = null;
	private static GP2InputStream gp2Stream = null;
	private static GP1InputStream gp1Stream = null;
	
	private static PTInputStream ptInputStream = null;
	
	private static ArrayList<TGInputStreamBase> supportedInputStreams = new ArrayList<TGInputStreamBase>();
	
	static {
		/*
		 * Init all the file formats
		 */
		gp1Stream = new GP1InputStream(GTPSettingsUtil.instance().getSettings());
		gp2Stream = new GP2InputStream(GTPSettingsUtil.instance().getSettings());
		gp3Stream = new GP3InputStream(GTPSettingsUtil.instance().getSettings());
		gp4Stream = new GP4InputStream(GTPSettingsUtil.instance().getSettings());
		gp5Stream = new GP5InputStream(GTPSettingsUtil.instance().getSettings());
		
		ptInputStream = new PTInputStream();
		
		supportedInputStreams.add(gp1Stream);
		supportedInputStreams.add(gp2Stream);
		supportedInputStreams.add(gp3Stream);
		supportedInputStreams.add(gp4Stream);
		supportedInputStreams.add(gp5Stream);
		supportedInputStreams.add(ptInputStream);
		
	}
	
	public static TabMetaData readTabMetaData(File tab) throws FileNotFoundException {
		return readTabMetaData(new FileInputStream(tab));
	}
	
	public static TabMetaData readTabMetaData(InputStream stream) {
		return null;
		
	}
	
}
