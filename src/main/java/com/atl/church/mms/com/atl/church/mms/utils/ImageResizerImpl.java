package com.atl.church.mms.com.atl.church.mms.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageResizerImpl implements ImageResizer {


	public BufferedImage resize(BufferedImage bufferedImage,int width,int height) throws Exception {
		try {
			return Thumbnails.of(bufferedImage)
					.height(height)
					.width(width)
					.asBufferedImage();
		} catch (IOException exception) {
			throw new Exception("Image could not be resized to paymentType: ");
		}
	}

	public BufferedImage resize(File file,int width,int height) throws Exception {
		BufferedImage bufferedImage = ImageIO.read(file);
		return resize(bufferedImage,width,height);
	}

}