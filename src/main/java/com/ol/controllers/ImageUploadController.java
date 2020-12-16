package com.ol.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ol.models.Image;
import com.ol.repositories.ImageRepository;
import com.ol.services.ImageService;

@RestController
@RequestMapping(path = "image")
public class ImageUploadController {

	@Autowired
	ImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Image img = new Image(file.getOriginalFilename(), file.getContentType(),
				file.getBytes());
		System.out.println(img.getPicByte().length);
		imageService.save(img);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping(path = { "/get/{imageId}" })
	public Image getImage(@PathVariable("imageId") Integer imageId) throws IOException {
		final Optional<Image> retrievedImage = imageService.findById(imageId);
		return retrievedImage.get();
	}
	
	@GetMapping(path = { "/delete/{imageId}" })
	public ResponseEntity<Object> deleteImage(@PathVariable("imageId") Integer imageId) throws IOException {
		try {
			imageService.delete(imageId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(path = { "/getAll" })
	public List<Image> getAllImage() throws IOException {
		
		return imageService.getAll();
	}
	
	
	
}
