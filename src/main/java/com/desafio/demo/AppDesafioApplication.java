package com.desafio.demo;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafio.demo.repository.ForecastRepository;

@SpringBootApplication
public class AppDesafioApplication {
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
		.baseUrl("https://api.hgbrasil.com")
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();
	}
	
		

	public static void main(String[] args) {
		SpringApplication.run(AppDesafioApplication.class, args);
	}
	

    }


