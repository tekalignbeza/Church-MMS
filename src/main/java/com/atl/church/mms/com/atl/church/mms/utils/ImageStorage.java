package com.atl.church.mms.com.atl.church.mms.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;


public interface ImageStorage {

	public static String RESOURCE_LOCATION_ROOT = "classpath:images/";
	public static String RESOURCE_LOCATION_TEMP = "classpath:images/tmp";
	public static String RESOURCE_LOCATION_PHOTO = "classpath:images/photo";
	public static String RESOURCE_LOCATION_BARCODE = "classpath:images/barcode";
	public static String RESOURCE_LOCATION_IDCARD = "classpath:images/id";

	File get(String fileName) throws FileNotFoundException;

	File save(BufferedImage bufferedImage, String fileName) throws Exception;
}