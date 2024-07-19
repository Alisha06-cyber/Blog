package com.test.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.services.FileService;
@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//full name
		String name=file.getOriginalFilename();
		 
		//random name gernator file
		String randomID=UUID.randomUUID().toString();
		String fileName=randomID.concat(name.substring(name.lastIndexOf(".")));
		//full path
		String filepath=path + File.separator + name;
		
		//craete folder if not crated
		File f=new File(path);
		if(!f.exists())
				f.mkdir();
		//filecopy
		
		Files.copy(file.getInputStream(), Paths.get(filepath));
		return name;
			
	}
	

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath=path + File.separator + fileName;
		InputStream is=new FileInputStream(fullpath);
		return is;
		//db logic to retun inputstram
	}

}
