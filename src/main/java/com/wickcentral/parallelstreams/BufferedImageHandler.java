package com.wickcentral.parallelstreams;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.imageio.ImageIO;

public class BufferedImageHandler {
	
	public static boolean exportImageToFile(File sourceImageFile, File resultImageFile, String imageFormatName) 
			throws IOException {

		ByteArrayOutputStream outStream = getImageOutputStream(sourceImageFile, imageFormatName);
		
		//read image file from local storage
		BufferedImage bufferedImage = ImageIO.read(sourceImageFile);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		// the buffer of the data to be converted into BufferedImage
		byte[] jpgBytArray = outStream.toByteArray();
		
		if ( jpgBytArray.length <=0 ) {
			throw new InvalidParameterException("Byte array is invalid for image '" + sourceImageFile.getAbsolutePath() +
					"'");
		}
		
		System.out.println("jpgBytArray size: " + jpgBytArray.length);
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		SampleModel sm = newImage.getSampleModel();
		DataBuffer buffer = newImage.getRaster().getDataBuffer();
		WritableRaster raster = Raster.createWritableRaster(sm, buffer, new Point(0,0));

		// input int pixel array
		// search bufferedimage "getRGB" for entire image
		int[] pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
		
//		for (int i = 0; i < pixels.length; i++){
//			pixels[i] = (pixels[i] & 0xff)/64;          // remove alpha channel
//		}

		
		raster.setPixels(0,0,width,height,pixels);
		
		
		// Convert the data array to Raster and use setData to fill the image
		//newImage.setData(Raster.createRaster(newImage.getSampleModel(), new DataBufferByte(jpgBytArray, jpgBytArray.length), new Point() ) );

		// write to jpeg file
		ImageIO.write(newImage, imageFormatName, resultImageFile);
  
		newImage.flush();
		
		if(resultImageFile.exists() && resultImageFile.length() > 0) { return true; }
		else return false;
		
	}
	
	
	public static ByteArrayOutputStream getImageOutputStream(File sourceImageFile, String imageFormatName) 
			throws IOException {
		
		//read image file from local storage
		BufferedImage bufferedImage = ImageIO.read(sourceImageFile);
		
		// write it to byte array in-memory for image format
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		if (!ImageIO.write(bufferedImage, imageFormatName, outStream)) {
			throw new IOException("Writing image '" + sourceImageFile.getAbsolutePath() + 
					"' to OutputStream caused exception");
		}
		
		bufferedImage.flush();
		
		return outStream;
	}

	
//	public static ByteArrayOutputStream getOutputStreamForImage(File sourceImageFile, File resultImageFile, String imageFormatName) 
//			throws IOException {
//		
//		  //read image file from local storage
//		BufferedImage bufferedImage = ImageIO.read(sourceImageFile);
//		
//		int width = bufferedImage.getWidth();
//		int height = bufferedImage.getHeight();
//		
//		// write it to byte array in-memory for image format
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		if (!ImageIO.write(bufferedImage, imageFormatName, outStream)) {
//			throw new IOException("Writing image '" + sourceImageFile.getAbsolutePath() + 
//					"' to OutputStream caused exception");
//		}
//		
//		// the buffer of the data to be converted into BufferedImage
//		byte[] jpgBytArray = outStream.toByteArray();
//		
//		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//		
//		// Convert the data array to Raster and use setData to fill the image, i.e.
//		newImage.setData(Raster.createRaster(newImage.getSampleModel(), new DataBufferByte(jpgBytArray, jpgBytArray.length), new Point() ) );
//
//		// write to jpeg file
//		ImageIO.write(newImage, imageFormatName, resultImageFile);
//  
//		newImage.flush();
//		
//		return null;
//	}

}
