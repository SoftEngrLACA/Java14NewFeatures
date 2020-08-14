package com.wickcentral;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDirFileManage {

	/**
	 * A parameterized test
	 * Pass complex arguments
	 * Use a method as an argument source
	 * Name supplied to @MethodSource needs to match an existing method
	 * 
	 * @throws IOException
	 */
	@DisplayName("Test Get Only Files")
	@ParameterizedTest
	@MethodSource("provideFilePaths")
	void testGetOnlyFiles(String absFilePath, String format) throws IOException {
		
		Path dir = Paths.get("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images");

		 System.out.println("absFilePath: " + absFilePath + " format: " + format);
		 
		com.wickcentral.utils.iofile.DirFileManage.getOnlyFiles(dir);
		
		assertEquals(format, "jpg");
	}
	
	/**
	 * 
	 * @return Stream of Arguments
	 */
	private static Stream<Arguments> provideFilePaths() {
		
		return Stream.of(
				Arguments.of("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\Aayla Secura_wallpaper.jpg", "JPG"),
				Arguments.of("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\sharks_jpeg2.jpg", "JPG"),
				Arguments.of("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\swfalcon08.jpg", "JPG"),
				Arguments.of("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\swrebel_fleet01.jpg", "JPG")
				);
		
	}

}
