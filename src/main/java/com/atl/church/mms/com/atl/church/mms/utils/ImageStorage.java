package com.atl.church.mms.com.atl.church.mms.utils;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public interface ImageStorage {

	public static String RESOURCE_LOCATION_ROOT = "./uploads/images/";
	public static String RESOURCE_LOCATION_TEMP = RESOURCE_LOCATION_ROOT+"tmp";
	public static String RESOURCE_LOCATION_PHOTO =  RESOURCE_LOCATION_ROOT+"photo";
	public static String RESOURCE_LOCATION_BARCODE =  RESOURCE_LOCATION_ROOT+"barcode";
	public static String RESOURCE_LOCATION_IDCARD =  RESOURCE_LOCATION_ROOT+"id";

	File get(String fileName) throws FileNotFoundException;

	File save(BufferedImage bufferedImage, String fileName) throws Exception;

	public File store(MultipartFile file, String newFileName) throws IOException;

}