package com.wickcentral.utils.iofile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirFileManage {
	
	final static public List<Path> getListOfOnlyFiles(Path dir) throws IOException {
		List<Path> paths = new ArrayList<>();
		if(Files.isDirectory(dir)) {
			try(Stream<Path> filesStream = Files.list(dir)) {
				paths = filesStream.filter(Files::isRegularFile)
				.collect(Collectors.toList());
			}

		}
		return paths;
	}
	
	
	final static public void getOnlyFiles(Path dir) throws IOException {
		if(Files.isDirectory(dir)) {
			try(DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
				for(Path file : files) {
					if(Files.isRegularFile(file)) {
						System.out.println(file.toAbsolutePath());
					}
				}
			}
		}
	}
	
    public final static String getFileExtension(String fileName) {
    	if ( fileName == null || fileName.length() <= 0 ) {
    		throw new IllegalArgumentException("File name is invalid");}
		int i = fileName.lastIndexOf('.');
		if (i > 0 && i < fileName.length() - 1) {
			return fileName.substring(i+1);
		} else return null;
    }
	

}
