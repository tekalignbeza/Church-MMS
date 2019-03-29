package com.atl.church.mms.com.atl.church.mms.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ImageStorageImpl implements ImageStorage {

	public File get(String fileName) throws FileNotFoundException {
		File file = ResourceUtils.getFile(fileName);
		return file;
	}

	public File save(BufferedImage bufferedImage, String fileName) throws Exception {
		try {
			File outputFile = new File(fileName);
			ImageIO.write(bufferedImage, "jpg", outputFile);
			return outputFile;
		} catch (IOException exception) {
			throw new Exception("Resized image could not be saved.");
		}
	}

}