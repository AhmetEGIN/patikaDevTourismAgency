package com.example.tourismAgency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourismAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismAgencyApplication.class, args);
	}
	
//	@Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("com.example.tourismAgency"))                                     
//          .build();
//
//    }
	
}
