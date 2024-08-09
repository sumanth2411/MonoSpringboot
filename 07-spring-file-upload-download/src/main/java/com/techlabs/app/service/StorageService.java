package com.techlabs.app.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techlabs.app.entity.ImageData;
import com.techlabs.app.repository.StorageRepository;
import com.techlabs.app.util.ImageUtil;

@Service
public class StorageService {
	
	private StorageRepository repository;

	public StorageService(StorageRepository repository) {
		super();
		this.repository = repository;
	}
	 
	 private final String Uploaded_Folder = "F:\\uploaded file";
	    
	    public ImageData uploadFile(MultipartFile file) throws IOException {
	      
		    ImageData image=new ImageData(0, file.getOriginalFilename(), file.getContentType(), ImageUtil.compressImage(file.getBytes()));
		    ImageData save = repository.save(image);
		    return save;
	      
	      
	    }
	    
	    public byte[] downloadFile(String fileName) throws IOException {
		    ImageData image=repository.findByName(fileName);
		    byte[] decompressImage = ImageUtil.decompressImage(image.getImageData());
		    return decompressImage;
	  }

	   
}
