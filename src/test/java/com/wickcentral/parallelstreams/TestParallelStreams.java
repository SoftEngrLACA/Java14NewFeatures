package com.wickcentral.parallelstreams;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TestParallelStreams {

	static File sourceImageFile;
	static File resultImageFile;
	static String imageFormatName;
	
	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll executed");
		sourceImageFile = new File("P:/TestJavaAppz/ParallelStreams/input/sharks_jpeg2.jpg");
		resultImageFile = new File("P:/TestJavaAppz/ParallelStreams/output/sharks_output");
		imageFormatName = "jpg";
	}

	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}

	@DisplayName("Export Image To File")
	@Tag("DEV")
	@Test
	void testExportImageToFile() {
		System.out.println("====== Test 1 Run =======");
		
		try {
			
			assertTrue(BufferedImageHandler.exportImageToFile(sourceImageFile, resultImageFile, imageFormatName), 
					"Invalid exported image for source Image File " + sourceImageFile.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearThis() {
		System.out.println("@AfterEach executed");
	}

	@AfterAll
	static void tear() {
		System.out.println("@AfterAll executed");
		sourceImageFile = resultImageFile = null;
	}

}
