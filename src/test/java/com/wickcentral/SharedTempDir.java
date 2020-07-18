package test.java.com.wickcentral;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class SharedTempDir {
	

	@TempDir
	static Path sharedTempDir; // To share a temporary directory between test methods: declare the field static
	// temporary files are actually created in system Temp dir, ex: windows temp C:\Temp


	@Test
	@Order(1)
	void givenFieldWithSharedTempDirectoryPath_whenWriteToFile_thenContentIsCorrect() throws IOException {
	Path numbers = sharedTempDir.resolve("numbers.txt");

	List<String> lines = Arrays.asList("1", "2", "3");
	Files.write(numbers, lines);
	

	assertAll(
	() -> assertTrue("File should exist", Files.exists(numbers)),
	() -> assertLinesMatch(lines, Files.readAllLines(numbers)));
	}

	@Test
	@Order(2)
	void givenAlreadyWrittenToSharedFile_whenCheckContents_thenContentIsCorrect() throws IOException {
	Path numbers = sharedTempDir.resolve("numbers.txt");

	assertLinesMatch(Arrays.asList("1", "2", "3"), Files.readAllLines(numbers));
	
	}
	
	@Test
	@Order(3)
	void findTempDir () throws IOException {
		Path tmpDirPath = Files.createTempDirectory("newTemp");
		System.out.println("tmpDirPath: " + tmpDirPath.toString());
	}
	

}
