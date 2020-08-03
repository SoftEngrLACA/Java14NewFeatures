package com.wickcentral.utils.images;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;

public class ImageFormats {

	public static void getImageFormats() {

		IIORegistry registry = IIORegistry.getDefaultInstance();
		Iterator<ImageWriterSpi> serviceProviders = registry.getServiceProviders(ImageWriterSpi.class, false);
		while (serviceProviders.hasNext()) {
			ImageWriterSpi next = serviceProviders.next();
			System.out.printf("description: %-27s   format names: %s	ImageReaderSpiNames: %s %n", 
					next.getDescription(Locale.ENGLISH),
					Arrays.toString(next.getFormatNames()),
					Arrays.toString(next.getImageReaderSpiNames())
					);
			
		}

	}

}
