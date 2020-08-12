package com.wickcentral;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wickcentral.parallelstreams.ImageFile;

public class TestImageFileRecord {
	
	static File sourceImageFile;
	static File resultImageFile;
	static String imageFormatName;
	static final String IMAGE_FORMATS[] = {"NFC", "PNG", "BMP", "TIFF", "GIF"};
	
	
	/**
	 * Junit 5: Assumptions 
	 */
	@DisplayName("Valid Image Formats")
	@Test
	void testValidImageFormats() {
		for (String format : IMAGE_FORMATS) {
			// invalid format
			if(format.equalsIgnoreCase("NFC")) {
				assumeFalse( ImageFile.isValidImageFormat(format));
			} 
			// valid formats
			else {
				assumeTrue( ImageFile.isValidImageFormat(format));	
			}
		}
	}
	
	
	@DisplayName("Valid Image File Record")
	@Test
	void testValidImageFileRecord() {
		sourceImageFile = new File("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\sharks_jpeg2.jpg");
		resultImageFile = new File("P:\\TestJavaAppz\\ParallelStreams\\output\\1_04_images\\sharks_out.jpg");
		imageFormatName = "jpg";
		
		
	}
	

}
