package com.wickcentral.utils.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageHandler {
	
	public static final boolean exportImageToFile(File sourceImageFile, File resultImageFile, String imageFormatName) 
			throws IOException {

		try (FileInputStream inputStream = new FileInputStream(sourceImageFile)) {
		
	        // reads input image from file
	        BufferedImage inputImage = ImageIO.read(inputStream);
	         
	        // writes to the output image in specified format
	        return ImageIO.write(inputImage, imageFormatName, resultImageFile);
	        
		}
		
		
	}


}
