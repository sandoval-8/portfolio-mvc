package com.portfolio.portfoliomvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.photo.repository.PhotoRepository;

@SpringBootApplication
public class PortfolioMvcApplication implements CommandLineRunner{
	
	private byte[] img;
	
	@Autowired
	private PhotoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioMvcApplication.class, args);
	}
/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("");
			}
		};
	} */
	
	
	@Override
	public void run(String... args) throws Exception {	
		
		/*			repository.save(new Photo(loadByteAllFile("C:\\Users\\lucas\\Desktop\\CODERHOUSE\\portfolio-v2.0\\src\\assets\\muestra\\1366x768(2).png")));
						repository.save(new Photo(loadByteAllFile("C:\\Users\\lucas\\Desktop\\CODERHOUSE\\portfolio-v2.0\\src\\assets\\muestra\\1366x768(2).png")));
		repository.save(new Photo(loadByteAllFile("C:\\Users\\lucas\\Desktop\\CODERHOUSE\\portfolio-v2.0\\src\\assets\\muestra\\1366x768(3).png")));
	*/	
	}
	
	private byte[] loadByteAllFile(String url) throws FileNotFoundException {
		
		FileInputStream fileInpStream = new FileInputStream(url);
		try {
			img = fileInpStream.readAllBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
