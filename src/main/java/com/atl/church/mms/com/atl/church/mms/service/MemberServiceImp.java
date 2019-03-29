package com.atl.church.mms.com.atl.church.mms.service;
import com.atl.church.mms.com.atl.church.mms.data.MemberRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.utils.ImageResizer;
import com.atl.church.mms.com.atl.church.mms.utils.ImageStorage;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private ImageResizer imageResizer;
	@Autowired
	private ImageStorage imageStorage;

	@Override
	public Member getMember(Long id){
		return memberRepo.getMember(id);
	}

	@Override
	public Member createMember(Member member){
		return memberRepo.createMember(member);
	}

	@Override
	public Member updateMember(Member member){
		return memberRepo.updateMember(member);
	}

	@Override
	public boolean deleteMember(Long id){
		return memberRepo.deleteMember(id);
	}

	@Override
	public boolean sendEmail(Long id, String msg){
		return true;
	}

	@Override
	public void upload(File tempFile, String id) {
		try {
			BufferedImage bufferedImage = imageResizer.resize(tempFile);
			Member member =  getMember(Long.parseLong(id));
			File photo = imageStorage.save(bufferedImage,ImageStorage.RESOURCE_LOCATION_PHOTO+"/"+id+"."+tempFile.getName().split("\\.")[1]);
			File barcode = generateBarcode(member);
			File idCard = generateId(photo,barcode,member);
			member.setIdCard(idCard.getAbsolutePath());
			updateMember(member);
		}catch (Exception e){

		}
	}

	private File generateId(File photo, File barcode,Member member) throws Exception {
		BufferedImage idCard = new BufferedImage(300, 420, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = idCard.createGraphics();
		g2d.drawImage(ImageIO.read(photo),25,20,null);
		g2d.drawImage(ImageIO.read(photo),25,340,null);
		g2d.dispose();
		 return imageStorage.save(idCard,ImageStorage.RESOURCE_LOCATION_IDCARD+"/"+member.getId()+".png");
	}

	private File generateBarcode(Member member) throws BarcodeException, OutputException {
		Barcode barcode = BarcodeFactory.createCode128B(member.getId()+member.getLastName());
		barcode.setBarHeight(60);
		barcode.setBarWidth(2);
		File barcodeFile = new File(ImageStorage.RESOURCE_LOCATION_BARCODE+"/"+member.getId()+".jpeg");
		BarcodeImageHandler.saveJPEG(barcode,barcodeFile);
		return barcodeFile;
	}

	@Override
	public InputStream getMemberIdCard(String id) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Member member = getMember(Long.parseLong(id));
		BufferedImage idCard  = ImageIO.read(imageStorage.get(member.getIdCard()));
		ImageIO.write(idCard, "jpg", outputStream);
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		return inputStream;
	}

}
