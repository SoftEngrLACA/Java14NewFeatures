package com.wickcentral.utils.iofile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileManage {
	
	final static public void getListOfOnlyFiles(Path dir) throws IOException {
		if(Files.isDirectory(dir)) {
			try(DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
				//files.map(Path::toAbsolutePath)
				
				
				
			}
		}
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
	
	

}
