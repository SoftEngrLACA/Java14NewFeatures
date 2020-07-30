package com.wickcentral.parallelstreams;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageHandler {
	
	public static ByteArrayOutputStream getOutputStreamForImage(File imageFile, String imageFormatName) throws IOException {
		
		  //read image file from local storage
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		
		// write it to byte array in-memory for image format
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		if (!ImageIO.write(bufferedImage, imageFormatName, outStream)) {
			throw new IOException("Writing image '" + imageFile.getAbsolutePath() + "' caused exception");
		}
		byte[] jpgBytArray = outStream.toByteArray();
		
		bufferedImage.flush();
		
		return null;
	}

}
