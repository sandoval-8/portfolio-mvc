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
	
	//==================================================
	
	@GetMapping("/{nombreFoto:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("src").resolve(nombreFoto).toAbsolutePath();
		Resource archivo = null;
		
		try {
			archivo = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(archivo,cabecera, HttpStatus.OK);
    }
	
	//==================================================
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
		Map<String,Object> response = new HashMap<>();		
		Post posteo = this.postService.getPostById(id).get();
		
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get("src").resolve(fileName).toAbsolutePath();
		//	Path filePath = Paths.get("C:\\Users\\lucas\\Desktop\\portfolio-v2.0 (workspace)\\portfolio-mvc\\src\\upload\\").resolve(fileName).toAbsolutePath();
			
			System.out.println("archivo:" + fileName);
			System.out.println("ruta:" + filePath);
		
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//-----------------------------------
			Photo savePhoto = new Photo();
			savePhoto.setPath(fileName);
			Photo savedPhoto = photoService.saveImage(savePhoto);
			
			System.out.println("FOTO GUARDADA, ID:" + savedPhoto.getId());
			
			// Ahora tengo que editar la tabla 'post.photo_id' para que el post referenie a la foto.
			
			PhotoOfPost saveEntity = new PhotoOfPost();
			saveEntity.setPostId(id);
			saveEntity.setPhotoId(savedPhoto.getId());
			
			photoPostService.guardar(saveEntity);
			
			
			
/*			posteo.setPhoto(photos);
			
			postService.putPost(posteo);
			response.put("cliente", posteo);
			response.put("mensaje", "Foto subida con exito: " + fileName); */
			
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
