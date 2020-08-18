package com.wickcentral;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wickcentral.utils.iofile.DirFileManage;

class TestDirFileManage {

	static final Path IMAGE_DIR_1_04 = Paths.get("P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images");
	static final Path MULTI_IMAGES_FORMATS = Paths.get("P:\\TestJavaAppz\\ParallelStreams\\input\\multi_images_formats");
	
	@BeforeAll
	static void setup() throws IOException {

		// just output only the files in this folder
		System.out.println("Output only the files from getListOfOnlyFiles for folder " + IMAGE_DIR_1_04 + ":");
		DirFileManage.getListOfOnlyFiles(IMAGE_DIR_1_04).forEach(path -> System.out.println(path.toAbsolutePath()));
		
		System.out.println("Output only the files from getListOfOnlyFiles for folder " + MULTI_IMAGES_FORMATS + ":");
		DirFileManage.getListOfOnlyFiles(MULTI_IMAGES_FORMATS).forEach(path -> System.out.println(path.toAbsolutePath()));
		
	}
	
	@DisplayName("File paths & files-list with multi-formats")
	@ParameterizedTest
	@MethodSource("provideFilePathsAndMultiFormats")
	void testFilePathsWithMultiFormats(String expectedpath, String expectedFormat, List<Path> actualFileList) {

		System.out.println("expectedpath: " + expectedpath + " expectedFormat: " + expectedFormat);
		 
		assertTrue( actualFileList.stream().anyMatch(
				// condition with combined conditions
				path -> 
					expectedpath.equalsIgnoreCase( getAbsolutePath(path) ) &&
					expectedFormat.equalsIgnoreCase( DirFileManage.getFileExtension(getAbsolutePath(path)) ) 
				),
				"Invalid actual path or format" // failure message
				);
		
	}
	
	
	/**
	 * A parameterized test to pass complex arguments
	 * Use a method as an argument source
	 * Name supplied to @MethodSource needs to match an existing method
	 */	
	private static Stream<Arguments> provideFilePathsAndMultiFormats() throws IOException {
		final String DIR_PREFIX = "P:\\TestJavaAppz\\ParallelStreams\\input\\multi_images_formats\\";
		List<Path> pathsFor_MULTI_IMAGES_FORMATS = DirFileManage.getListOfOnlyFiles(MULTI_IMAGES_FORMATS);
		return Stream.of(
				Arguments.of( (DIR_PREFIX + "sharks.bmp"), 			"BMP", pathsFor_MULTI_IMAGES_FORMATS ), // BMP
				Arguments.of( (DIR_PREFIX + "swfalcon08.gif"), 		"GIF", pathsFor_MULTI_IMAGES_FORMATS ), // GIF
				Arguments.of( (DIR_PREFIX + "swrebel_fleet.png"),	"PNG", pathsFor_MULTI_IMAGES_FORMATS ) // PNG
				);
	}
	
	/**
	 * A parameterized test to pass complex arguments
	 * Use a method as an argument source
	 * Name supplied to @MethodSource needs to match an existing method
	 */
	@DisplayName("File paths and formats with list of files")
	@ParameterizedTest
	@MethodSource("provideFilePathsAndFormats")
	void testFilePathsAndFormatsWithListOfFiles(String expectedpath, List<Path> fileList, String format) {

		 System.out.println("expectedpath: " + expectedpath + " format: " + format);
		 
		 // granular failure messages with a single condition
		 assertTrue( fileList.stream().anyMatch(
					 // condition
					 path -> expectedpath.equalsIgnoreCase( getAbsolutePath(path) ) 
					 ),
					 "Invalid actual path" // failure message
				 );
		 
		 assertTrue( fileList.stream().anyMatch(
					 // condition
					 path -> format.equalsIgnoreCase( DirFileManage.getFileExtension(getAbsolutePath(path)) ) 
					 ),
					 "Invalid actual format" // failure message
				 );
		 
//		 assertTrue( fileList.stream().anyMatch(
//				 // condition with combined conditions
//				 path -> 
//						 expectedpath.equalsIgnoreCase( getAbsolutePath(path) ) &&
//						 format.equalsIgnoreCase( DirFileManage.getFileExtension(getAbsolutePath(path)) ) 
//				 		),
//				 "Invalid actual path or format" // failure message
//				 );
		
	}

	private static Stream<Arguments> provideFilePathsAndFormats() throws IOException {
		final String DIR_PREFIX = "P:\\TestJavaAppz\\ParallelStreams\\input\\1_04_images\\";
		List<Path> pathsFor_IMAGE_DIR_1_04 = DirFileManage.getListOfOnlyFiles(IMAGE_DIR_1_04);
		return Stream.of(
				Arguments.of( (DIR_PREFIX + "Aayla Secura_wallpaper.jpg"), pathsFor_IMAGE_DIR_1_04, "jPG" ),
				Arguments.of( (DIR_PREFIX + "sharks_jpeg2.jpg"), pathsFor_IMAGE_DIR_1_04, "JPG" ),
				Arguments.of( (DIR_PREFIX + "swfalcon08.jpg"), pathsFor_IMAGE_DIR_1_04, "JPG" ),
				Arguments.of( (DIR_PREFIX + "swrebel_fleet01.jpg"), pathsFor_IMAGE_DIR_1_04, "JPG" )
				);
	}
	
	
	/**
	 * A parameterized test to pass complex arguments
	 * Use a method as an argument source
	 * Name supplied to @MethodSource needs to match an existing method
	 */
	@DisplayName("Parameterized test to pass complex args")
	@ParameterizedTest
	@MethodSource("provideFilePaths")
	void testParameterizedTestWithMethodSource(String absFilePath, String format) {

		 System.out.println("absFilePath: " + absFilePath + " format: " + format);
		 
		 String fileExtString = DirFileManage.getFileExtension(absFilePath).toUpperCase();
	
		 assertTrueIgnoreCaseWithExplanation( ("File format for file " + absFilePath), format, fileExtString);
		
	}
	
	private String getAbsolutePath(Path path) {
		return path.toAbsolutePath().toString();
	}
	
	/**
	 * A helper method
	 * @param description
	 * @param expectedString
	 * @param actualString
	 */
	private void assertTrueIgnoreCaseWithExplanation(final String description, final String expectedString, final String actualString){
		assertTrue(expectedString.equalsIgnoreCase(actualString),
		() ->  String.format("%1$s -> The expected is: '%2$s' while the actual is: '%3$s'", description, expectedString, actualString));
	}
	
	/**
	 * IMAGE_DIR_1_04
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
