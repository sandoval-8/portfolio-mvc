package com.portfolio.portfoliomvc.photo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.photo.service.IPhotoService;
import com.portfolio.portfoliomvc.post.entity.Post;
import com.portfolio.portfoliomvc.post.service.IPostService;
import com.portfolio.portfoliomvc.relations.SamplePhoto;
import com.portfolio.portfoliomvc.relations.service.IPhotoPostService;
import com.portfolio.portfoliomvc.util.random.RandomFileName;

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
	
	//   src/upload
	public String volumen = Environment.getProperties().getProperty("portfolio.volumen.url");

	// ==================================================
	// =================== GET IMAGE ====================
	// ==================================================

	@GetMapping("/{nombreFoto:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String nombreFoto) {
		log.info("volumen:" + volumen);
		
		Path rutaArchivo = Paths.get(volumen).resolve(nombreFoto).toAbsolutePath();
		Resource archivo = null;
		log.info(rutaArchivo.toString());
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
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, 
							@RequestParam("id") Long idPost, 
							@RequestParam("isSample") boolean isSample) {
		
		Map<String, Object> response = new HashMap<>(); 
		Post posteo = this.postService.getPostById(idPost).get();
		
		if (!file.isEmpty()) {
			Optional<Post> post = postService.getPostById(idPost);
			if (post.isPresent()) {
				
				String nombreArchivo = file.getOriginalFilename();
				String extension = nombreArchivo.split("\\.")[1];			
				String fileName = RandomFileName.generateRandomUUID().concat("." + extension);
				System.out.println(volumen);
				Path filePath = Paths.get(volumen).resolve(fileName).toAbsolutePath();
				
				saveFile(file, filePath); //Guarda 'file' en el volumen
				Photo savedPhoto = savePhoto(fileName); //Persiste en PHOTO
				
				if(isSample) {//Si es true lo guardamos dentro de POST
					
					saveSamplePhoto(idPost, savedPhoto.getId()); //Persiste en SAMPLE_PHOTO
					
					Post postSaving = post.get();
					postSaving.setSamplePhoto(savedPhoto);
					postService.putPost(postSaving);
					
				} else if(!isSample) {
					//Si es falso lo guardamos en PhotoOfPost
					
					
				} else {
					log.debug("'isSample' is not defined or is null");
				}
			} else {
				response.put("messegeError", "post no encontrado");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//Almacena 'file' en el servidor
	private void saveFile(MultipartFile file, Path filePath) {
		try {
			Files.copy(file.getInputStream(), filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Persiste en PHOTO
	private Photo savePhoto(String fileName) {
		Photo savePhoto = new Photo();
		savePhoto.setNameResource(fileName);
		Photo savedPhoto = photoService.saveImage(savePhoto);
		return savedPhoto;
	}
	
	//Persiste en SAMPLE_PHOTO
	private SamplePhoto saveSamplePhoto(Long idPost, Long idPhoto) {
		SamplePhoto saveEntity = new SamplePhoto();
		saveEntity.setPostId(idPost);
		saveEntity.setPhotoId(idPhoto);
		return photoPostService.guardar(saveEntity);
	}
	
}