package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.utils.ImageResizer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class UploadServiceImp implements UploadService {

	@Autowired
	ImageResizer imageResizer;
	private int idWidth = 250;
	private int idHeight = 350;
	private int idCenter = idWidth/2;
	private double photoWidthPercentile=.5;
	private double photoHeightPercentile=.5;
	private double txtPanelWidthPercentile=.8;
	private double txtPanelHeightPercentile=.15;
	private double barcodeHeightPercentile=.15;
	private double innerPaddingHeightPercentile=.05;
	private double outerPaddingHeightPercentile=.05;
	private int innerPaddingHeight = (int) (idHeight * innerPaddingHeightPercentile);
	private int outerPaddingHeight = (int) (idHeight* outerPaddingHeightPercentile);
	private int photoWidth = (int) (idWidth*photoWidthPercentile);
	private int photoHeight  = (int) (idHeight*photoHeightPercentile);
	private int txtPanelWidth = (int) (idWidth*txtPanelWidthPercentile);
	private int txtPanelHeight = (int) (idHeight*txtPanelHeightPercentile);
	private int barcodeHeight = (int) (idHeight*barcodeHeightPercentile);
	/*
	* outer padding 5%
	* photo 50%
	* inner padding 2.5%
	* txt 20%
	* inner padding 2.5%
	* barcode 15%
	* outer padding 5%
	* */

	@Override
	public BufferedImage GenerateIdCard(File tempFile, Member member) throws Exception {
		BufferedImage idCardBufferedImage = new BufferedImage(idWidth, idHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D idCard = idCardBufferedImage.createGraphics();
		idCard.setColor(Color.WHITE);
		idCard.fillRect(0, 0, idWidth, idHeight);
		idCard.setColor(Color.BLACK);
		drawPhoto(idCard,tempFile);
		drawBarcode(idCard,member);
		drawTxt(idCard,member);
		idCard.dispose();
		return idCardBufferedImage;
	}

	@Override
	public void drawPhoto(Graphics2D idCard, File tempFile) throws Exception {
		BufferedImage photo = imageResizer.resize(tempFile,photoWidth,photoHeight);
		int photoCenter = photo.getWidth()/2;
		idCard.drawImage(photo,idCenter-photoCenter,outerPaddingHeight ,null);
	}

	@Override
	public BufferedImage generateBarcode(Member member) throws Exception {
		Barcode barcode = BarcodeFactory.createCode128B(member.getLastName()+member.getId());
		barcode.setBarWidth(2);
		barcode.setBarHeight(40);
		return BarcodeImageHandler.getImage( barcode);
	}

	@Override
	public void drawTxt(Graphics2D idCard, Member member){
		int txtCenter = txtPanelWidth/2;
		int txtPanelFontSize = 20;
		idCard.setColor(Color.WHITE);
		idCard.fillRect(idCenter-txtCenter, outerPaddingHeight+photoHeight+innerPaddingHeight, txtPanelWidth, txtPanelHeight);
		idCard.setColor(Color.BLACK);
		idCard.setFont(new Font("Arial Black", Font.BOLD, txtPanelFontSize));
		writeTxt(idCard,member);
	}

	private void writeTxt(Graphics2D idCard, Member member){

		String name = member.getFirstName().toUpperCase()+" "+member.getLastName().toUpperCase();
		String id = String.format("%04d", member.getId());
		FontMetrics fm = idCard.getFontMetrics();
		int nameWidth = 0;
		int idWidth = 0;
		if(fm.stringWidth(name)>txtPanelWidth){
			idCard.setFont(new Font("Arial Black", Font.BOLD, 10));
			fm = idCard.getFontMetrics();
			nameWidth = (idCenter - fm.stringWidth(name)/2);
			idWidth =  (idCenter - fm.stringWidth(id)/2);
		}else {
			nameWidth = (idCenter - fm.stringWidth(name)/2);
			idWidth =  (idCenter - fm.stringWidth(id)/2);
		}
		idCard.drawString(name, nameWidth, outerPaddingHeight+photoHeight+innerPaddingHeight+25);
		idCard.drawString(id, idWidth, outerPaddingHeight+photoHeight+innerPaddingHeight+45);
	}

	@Override
	public void drawBarcode(Graphics2D idCard, Member member) throws Exception {
		BufferedImage barcode = generateBarcode(member);
		int barcodeCenter = barcode.getWidth()/2;
		idCard.drawImage(barcode,idCenter-barcodeCenter,outerPaddingHeight+photoHeight+innerPaddingHeight+txtPanelHeight+innerPaddingHeight ,null);
	}
}
