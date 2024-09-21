package com.api.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	//Upload File Directory -> File will be upload at this location
	//public final String UPLOAD_DIR="D:\\Muskan Garg\\JavaSpringBoot\\SpringBootRestBook\\src\\main\\resources\\static\\images";
	
	
	//Upload Image Dynamically -> No need to provide the full path
	public final String UPLOAD_DIR=new ClassPathResource("static/images/").getFile().getAbsolutePath();	
	
	public FileUploadHelper() throws IOException     //Handling exception for ClassPathResource using constructor
	{
		
	}
	
	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean f = false;
		try {
			//read file
			InputStream fis = multipartFile.getInputStream();
			byte data[] = new byte[fis.available()];
			fis.read(data);
			
			//write file
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+File.separator+multipartFile.getOriginalFilename());
			fos.write(data);
			
			fos.flush();
			fos.close();
			
			//Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+"\\"+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
