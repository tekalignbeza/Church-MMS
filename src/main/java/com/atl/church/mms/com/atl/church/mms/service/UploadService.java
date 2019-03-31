package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public interface UploadService {
	BufferedImage GenerateIdCard(File tempFile, Member member) throws Exception;

	void drawPhoto(Graphics2D idCard, File tempFile) throws Exception;

	void drawBarcode(Graphics2D idCard, Member member) throws Exception;

	BufferedImage generateBarcode(Member member) throws Exception;

	void drawTxt(Graphics2D idCard, Member member);
}
