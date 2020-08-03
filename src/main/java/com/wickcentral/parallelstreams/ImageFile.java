package com.wickcentral.parallelstreams;

import java.io.File;
import java.util.Objects;

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
			Objects.requireNonNull(resultImageFile, "Result image file is required.");
			Objects.requireNonNull(imageFormatName, "Image format name is required.");
		}
		
	}

}
