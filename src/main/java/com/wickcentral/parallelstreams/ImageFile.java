package com.wickcentral.parallelstreams;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Using Java 14 New Feature: Record - a preview language feature
 */
public class ImageFile {
	
	@SuppressWarnings("preview")
	public record ImageFileRecord(File sourceImageFile, File resultImageFile, String imageFormatName) {
	
		/**
		 * Compact Constructor with data validation 
		 */
		public ImageFileRecord {
			Objects.requireNonNull(sourceImageFile, "Source image file is required.");
			if(!sourceImageFile.exists()) {throw new IllegalArgumentException("Invalid source image file " + sourceImageFile);}
			Objects.requireNonNull(resultImageFile, "Result image file is required.");
			Objects.requireNonNull(imageFormatName, "Image format name is required.");
		}
		
	}
	
	public static boolean isValidImageFormat(String imageFormatName) {
		return imageFormats().stream().anyMatch( imageFormatName::equalsIgnoreCase );
	}
	
	public static Set<String> imageFormats() {
		Set<String> formatsSet = new HashSet<>();
		formatsSet.add("PNG");
		formatsSet.add("BMP");
		formatsSet.add("TIFF");
		formatsSet.add("GIF");
		return formatsSet;
	}

}
