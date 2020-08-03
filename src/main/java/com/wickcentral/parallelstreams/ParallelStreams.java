package com.wickcentral.parallelstreams;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wickcentral.parallelstreams.ImageFile.ImageFileRecord;

public class ParallelStreams {

	private static Logger log;
	private static final String LOG_FILE = "P:\\TestJavaAppz\\ParallelStreams\\output\\app.log";

	public static void main(String[] args) {

		StringBuilder logMessages = new StringBuilder("Input args\n");
		
		try {
			
			logMessages.append("logFile: " + LOG_FILE);
			
	        // set system property before log4j2 is initialized
	        System.setProperty("logFilename", LOG_FILE);
	        log = LogManager.getLogger(ParallelStreams.class);
	        
	        log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss a"))+ "\n");
		    
	        
	        ImageFileRecord imageFileRecord;
	        File sourceImageFile, resultImageFile;
	        
	        // add Data
	        sourceImageFile = new File("P:/TestJavaAppz/ParallelStreams/input/sharks_jpeg2.jpg");
	        resultImageFile = new File("P:/TestJavaAppz/ParallelStreams/output/sharks_output.png");
	        imageFileRecord = new ImageFileRecord(sourceImageFile, resultImageFile, "png");
	        
	        log.info("ParallelStreams-imageFileRecord: " + imageFileRecord.toString());
			
			
		} catch (Exception e)  {
			System.err.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
			log.atError().withLocation().withThrowable(e).log(
			        "Errors for {}", logMessages);
		} finally {
			System.out.println("... ParallelStreams DONE!");
		}
	

	}

}
