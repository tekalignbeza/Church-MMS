package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Member2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public interface UploadService {
	BufferedImage GenerateIdCard(File tempFile, Member2 member2) throws Exception;

	void drawPhoto(Graphics2D idCard, File tempFile) throws Exception;

	void drawBarcode(Graphics2D idCard, Member2 member2) throws Exception;

	BufferedImage generateBarcode(Member2 member2) throws Exception;

	void drawTxt(Graphics2D idCard, Member2 member2);
}
