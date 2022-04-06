package com.portfolio.portfoliomvc.photo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.photo.service.IPhotoHQLService;
import com.portfolio.portfoliomvc.photo.service.IPhotoService;
import com.portfolio.portfoliomvc.photo.service.impl.PhotoServiceImpl;
import com.portfolio.portfoliomvc.post.entity.Post;
import com.portfolio.portfoliomvc.post.service.IPostService;
import com.portfolio.portfoliomvc.relations.PhotoOfPost;
import com.portfolio.portfoliomvc.relations.service.IPhotoPostService;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	private static Logger log = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	private IPhotoService photoService;

	@Autowired
	private IPostService postService;

	@Autowired
	private IPhotoPostService photoPostService;

	// ==================================================
	// =================== GET IMAGE ====================
	// ==================================================

	@GetMapping("/{nombreFoto:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("src/upload").resolve(nombreFoto).toAbsolutePath();
		Resource archivo = null;

		try {
			archivo = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getFilename() + "\"");

		return new ResponseEntity<Resource>(archivo, cabecera, HttpStatus.OK);
	}

	// ==================================================
	// ================== POST IMAGE ====================
	// ==================================================

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long idPost) {
		Map<String, Object> response = new HashMap<>();
		Post posteo = this.postService.getPostById(idPost).get();
		
		// (1) STEP ONE: retrive the resource from the request(MultipartFile).
		if (!file.isEmpty()) {
			
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get("src/upload").resolve(fileName).toAbsolutePath(); //Donde lo va a almacenar.

			// (2) STEP TWO: save the resource to the path specified.
			saveFile(file, filePath); //Guarda el 'file' en el 'filePath' especificado.
			
			// -----------------------------------
			// (3) STEP THREE: Crea el registro 'Photo' con la URL donde esta guardado.
			Photo savedPhoto = savePhoto(fileName);
			// -----------------------------------
			
			// (4) STEP FOUR: Crea el registro 'Post' con
			savePhotoOfPost(idPost, savedPhoto.getId());
			
			// -----------------------------------

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	private void saveFile(MultipartFile file, Path filePath) {
		try {
			Files.copy(file.getInputStream(), filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------------------
	
	private Photo savePhoto(String fileName) {
		Photo savePhoto = new Photo();
		savePhoto.setNameResource(fileName);
		Photo savedPhoto = photoService.saveImage(savePhoto);
		return savedPhoto;
	}
	
	private PhotoOfPost savePhotoOfPost(Long idPost, Long idPhoto) {
		PhotoOfPost saveEntity = new PhotoOfPost();
		saveEntity.setPostId(idPost);
		saveEntity.setPhotoId(idPhoto);
		return photoPostService.guardar(saveEntity);
	}
}
