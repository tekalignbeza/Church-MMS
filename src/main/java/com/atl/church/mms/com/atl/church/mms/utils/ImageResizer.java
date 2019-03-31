package com.atl.church.mms.com.atl.church.mms.utils;

import java.awt.image.BufferedImage;
import java.io.File;


public interface ImageResizer {


	BufferedImage resize(BufferedImage bufferedImage,int width,int height) throws Exception;
	BufferedImage resize(File file,int width,int height) throws Exception;


}