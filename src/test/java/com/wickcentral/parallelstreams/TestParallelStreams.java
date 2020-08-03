package com.wickcentral.parallelstreams;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.wickcentral.utils.images.BufferedImageHandler;

class TestParallelStreams {

	static File sourceImageFile;
	static File resultImageFile;
	static String imageFormatName;
	
	@BeforeAll
	static void setup() {
		
		// in
		sourceImageFile = new File("P:/TestJavaAppz/ParallelStreams/input/sharks_jpeg2.jpg");
		
		// out
		imageFormatName = "png";
		resultImageFile = new File("P:/TestJavaAppz/ParallelStreams/output/sharks_output.png");
		
	}

	@DisplayName("Export Image To File")
	@Tag("DEV")
	@Test
	void testExportImageToFile() {
		
		try {
			
			assertTrue(BufferedImageHandler.exportImageToFile(sourceImageFile, resultImageFile, imageFormatName), 
					"Invalid exported image for source Image File " + sourceImageFile.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	static void tear() {
		sourceImageFile = resultImageFile = null;
	}

}
