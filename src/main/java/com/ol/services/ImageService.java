package com.ol.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Image;
import com.ol.repositories.ImageRepository;


@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	public Image save(Image img) {
		return imageRepository.save(img);
	}

	public Optional<Image> findById(Integer imageId) {
		// TODO Auto-generated method stub
		return imageRepository.findById(imageId);
	}
	
	public List<Image> getAll() {
		return (List<Image>) imageRepository.findAll();
		
	}
	
	// compress the image bytes before storing it in the database
		public byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
		}
		// uncompress the image bytes before returning it to the angular application
		public byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}

		public void delete(Integer imageId) {
			// TODO Auto-generated method stub
			imageRepository.delete(imageRepository.findById(imageId).get());;
		}

}
