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


	public BufferedImage resize(BufferedImage bufferedImage) throws Exception {
		//todo externalize height and width configs
		final int height = 300;
		final int width = 250;
		try {
			return Thumbnails.of(bufferedImage)
					.height(height)
					.width(width)
					.asBufferedImage();
		} catch (IOException exception) {
			throw new Exception("Image could not be resized to type: ");
		}
	}

	public BufferedImage resize(File file) throws Exception {
		BufferedImage bufferedImage = ImageIO.read(file);
		return resize(bufferedImage);
	}

}