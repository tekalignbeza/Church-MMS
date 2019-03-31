package com.atl.church.mms.com.atl.church.mms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageStorageImpl implements ImageStorage {

	public ImageStorageImpl() throws Exception {
		List<String> uploadLocation = Arrays.asList(RESOURCE_LOCATION_TEMP,RESOURCE_LOCATION_PHOTO,RESOURCE_LOCATION_BARCODE,RESOURCE_LOCATION_IDCARD);
		try {
			for(String location:uploadLocation){
				Files.createDirectories(Paths.get(location).toAbsolutePath().normalize());
			}
		} catch (Exception ex) {
			throw new Exception("Could not create the directory where the uploaded files will be stored.");
		}

	}


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

	public File store(MultipartFile file, String newFileName) throws IOException {
		String extension = file.getOriginalFilename().split("\\.")[1];
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path temp = Paths.get(RESOURCE_LOCATION_TEMP).toAbsolutePath().normalize();
		Path targetLocation = temp.resolve(newFileName + "." + extension);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		return targetLocation.toFile();
	}

}