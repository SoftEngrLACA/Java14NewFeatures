package com.wickcentral;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.wickcentral.parallelstreams.ImageFile;

public class TestImageFileRecord {
	
	static File sourceImageFile;
	static File resultImageFile;
	static String imageFormatName;
	static final String IMAGE_FORMATS[] = {"NFC", "PNG", "BMP", "TIFF", "GIF"};
	
	/**
	 * ParameterizedTest with placeholders for current invocation index (1-based)
	 * and complete & comma-separated arguments list
	 * @param format the image format
	 */
	@DisplayName("Image Formats with ValueSource")
	@ParameterizedTest(name = "Image Format  #{index} = [{arguments}]")
	@ValueSource(strings = {"NFC", "", "PNG", "BMP", "TIFF", "GIF"})
	void testImageFormatsWithArrayAsSourceOfArguments(String format) {
		
		// Junit 5: Assumptions
		assumeTrue( (Objects.nonNull(format) && !format.trim().isEmpty()), 
				// message if the assumption is invalid
				"Format is invalid" );
		
	}
	
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
