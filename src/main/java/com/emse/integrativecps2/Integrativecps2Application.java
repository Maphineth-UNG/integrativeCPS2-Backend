package com.emse.integrativecps2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@EnableScheduling // Enable scheduled rule evaluations
public class Integrativecps2Application {
	public static void main(String[] args) {
		SpringApplication.run(Integrativecps2Application.class, args);
	}


//	@Configuration
//	public class CorsConfig {
//		@Bean
//		public WebMvcConfigurer corsConfigurer() {
//			return new WebMvcConfigurer() {
//				@Override
//				public void addCorsMappings(CorsRegistry registry) {
//					registry.addMapping("/**").allowedOrigins("*");
//				}
//			};
//		}
//	}

	// Enable CORS globally for frontend communication
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Allow all endpoints
						.allowedOrigins("*") // Allow requests from any origin (adjust as needed)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
						.allowedHeaders("*"); // Allow all headers
			}
		};
	}
}


