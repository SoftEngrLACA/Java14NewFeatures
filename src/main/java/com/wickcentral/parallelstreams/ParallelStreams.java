package com.wickcentral.parallelstreams;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wickcentral.parallelstreams.ImageFile.ImageFileRecord;
import com.wickcentral.utils.images.BufferedImageHandler;

public class ParallelStreams {

	private static final String LOG_FILE = "P:\\TestJavaAppz\\ParallelStreams\\output\\app.log";
	private static final int PARALLELISM = 4;
	private static Logger log;

	
	public static void main(String[] args) {

		StringBuilder logMessages = new StringBuilder("Input args\n");
		
		try {
			
			logMessages.append("logFile: " + LOG_FILE);
			
	        // set system property before log4j2 is initialized
	        System.setProperty("logFilename", LOG_FILE);
	        log = LogManager.getLogger(ParallelStreams.class);
	        
	        log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss a"))+ "\n");
		    
	        com.wickcentral.utils.images.ImageFormats.getImageFormats();
	        
	        List<ImageFileRecord> imageRecords = getImageData();
	        
	        //changeFormatWithDefaultForkJoinPool(imageRecords);
	        
	        changeFormatWithCustomThreadPool(imageRecords, PARALLELISM);

			
		} catch (Exception e)  {
			System.err.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
			log.atError().withLocation().withThrowable(e).log(
			        "Errors for {}", logMessages);
		} finally {
			System.out.println("... ParallelStreams DONE!");
		}
	

	}
	
/**
 * To avoid using the common shared thread pool
 *  
 * @param imageRecords
 * @param parallelism the parallelism level; experiment to determine the optimal value, 
 * Example: can a number based on how many cores the system CPU has
 * @throws ExecutionException 
 * @throws InterruptedException 
 */
	private static void changeFormatWithCustomThreadPool(List<ImageFileRecord> imageRecords, int parallelism) 
			throws InterruptedException, ExecutionException {
		
		long start = System.currentTimeMillis();
		
		ForkJoinPool forkPool = new ForkJoinPool(parallelism);
		
		forkPool.submit(
				() -> imageRecords.parallelStream().forEach(imageRecord -> {
		        	
		        	log.info("ImageFileRecord: " + imageRecord.toString());
		        	
		        	try {
						BufferedImageHandler.exportImageToFile(
								imageRecord.sourceImageFile(), 
								imageRecord.resultImageFile(), 
								imageRecord.imageFormatName());
					} catch (IOException e) {
						throw new RuntimeException(
								"For image file: " + imageRecord.toString(), 
								e);
					}
		        	
		        })				

				).get();
		
		forkPool.shutdown();
		
		log.info("Duration: " + (System.currentTimeMillis() - start) );
		
	}
	
	private static void changeFormatWithDefaultForkJoinPool(List<ImageFileRecord> imageRecords) {
        // default ForkJoinPool
		imageRecords.parallelStream().forEach(imageRecord -> {
        	
        	log.info("ImageFileRecord: " + imageRecord.toString());
        	
        	try {
				BufferedImageHandler.exportImageToFile(
						imageRecord.sourceImageFile(), 
						imageRecord.resultImageFile(), 
						imageRecord.imageFormatName());
			} catch (IOException e) {
				throw new RuntimeException(
						"For image file: " + imageRecord.toString(), 
						e);
			}
        	
        }  );
		/**
Sample output:

[ForkJoinPool.commonPool-worker-7] com.wickcentral.parallelstreams.ParallelStreams:lambda$0(37): ParallelStreams-imageFileRecord: ImageFileRecord[sourceImageFile=P:\TestJavaAppz\ParallelStreams\input\sharks_jpeg2.jpg, resultImageFile=P:\TestJavaAppz\ParallelStreams\output\sharks_output.png, imageFormatName=PNG]
[main] com.wickcentral.parallelstreams.ParallelStreams:lambda$0(37): ParallelStreams-imageFileRecord: ImageFileRecord[sourceImageFile=P:\TestJavaAppz\ParallelStreams\input\swfalcon08.jpg, resultImageFile=P:\TestJavaAppz\ParallelStreams\output\swfalcon08_output.tif, imageFormatName=TIFF]
[ForkJoinPool.commonPool-worker-3] com.wickcentral.parallelstreams.ParallelStreams:lambda$0(37): ParallelStreams-imageFileRecord: ImageFileRecord[sourceImageFile=P:\TestJavaAppz\ParallelStreams\input\swrebel_fleet01.jpg, resultImageFile=P:\TestJavaAppz\ParallelStreams\output\swrebel_fleet01_output.gif, imageFormatName=GIF]
[ForkJoinPool.commonPool-worker-5] com.wickcentral.parallelstreams.ParallelStreams:lambda$0(37): ParallelStreams-imageFileRecord: ImageFileRecord[sourceImageFile=P:\TestJavaAppz\ParallelStreams\input\Aayla Secura_wallpaper.jpg, resultImageFile=P:\TestJavaAppz\ParallelStreams\output\Aayla Secura_output.bmp, imageFormatName=BMP]

		 */
	}
	
	
	private static List<ImageFileRecord> getImageData() {
		
		List<ImageFileRecord> imageRecords = new ArrayList<>();
        ImageFileRecord imageFileRecord;
        String srcPrefix = "P:/TestJavaAppz/ParallelStreams/input/";
        String destinPrefix = "P:/TestJavaAppz/ParallelStreams/output/";

        
        // add Data
        imageFileRecord = new ImageFileRecord(
        		new File(srcPrefix + "sharks_jpeg2.jpg"), 
        		new File(destinPrefix + "sharks_output.png"), 
        		"PNG");
        imageRecords.add(imageFileRecord);
        
        imageFileRecord = new ImageFileRecord(
        		new File(srcPrefix + "Aayla Secura_wallpaper.jpg"), 
        		new File(destinPrefix + "Aayla Secura_output.bmp"), 
        		"BMP");
        imageRecords.add(imageFileRecord);
        
        imageFileRecord = new ImageFileRecord(
        		new File(srcPrefix + "swfalcon08.jpg"), 
        		new File(destinPrefix + "swfalcon08_output.tif"), 
        		"TIFF");
        imageRecords.add(imageFileRecord);
        
        imageFileRecord = new ImageFileRecord(
        		new File(srcPrefix + "swrebel_fleet01.jpg"), 
        		new File(destinPrefix + "swrebel_fleet01_output.gif"), 
        		"GIF");
        imageRecords.add(imageFileRecord);
        
        // Throw Exception
//        imageFileRecord = new ImageFileRecord(
//        		new File(srcPrefix + "nonExistingFile.jpg"), 
//        		new File(destinPrefix + "nonExistingFile_output.gif"), 
//        		"GIF");
//        imageRecords.add(imageFileRecord);
        /**
         * Sample Stack trace:
         *  
         *  Caused by: java.lang.RuntimeException: For image file: 
         *  ImageFileRecord[sourceImageFile=P:\TestJavaAppz\ParallelStreams\input\nonExistingFile.jpg, 
         *  resultImageFile=P:\TestJavaAppz\ParallelStreams\output\nonExistingFile_output.gif, 
         *  imageFormatName=GIF]
         *  at com.wickcentral.parallelstreams.ParallelStreams.lambda$0(ParallelStreams.java:66)
         * 
         * Caused by: java.io.FileNotFoundException: P:\TestJavaAppz\ParallelStreams\input\nonExistingFile.jpg (The system cannot find the file specified)
         */

        return imageRecords;
		
	}
	

}
