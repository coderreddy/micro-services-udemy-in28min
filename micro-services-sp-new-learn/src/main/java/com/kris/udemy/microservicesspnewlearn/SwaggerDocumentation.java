package com.kris.udemy.microservicesspnewlearn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentation {
	
	//Config to define the contact details and also API doc
	public static final Contact DEFAULT_CONTACT = new Contact("Krish", "http://termsAndConditions", "abc@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Krish's Api", "Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private Set<String> DEFAULT_PRODUCE_AND_CONSUME = new HashSet<String>();
	//Creates the documentation for the controller resources.
	@Bean
	public Docket api(){
		DEFAULT_PRODUCE_AND_CONSUME.add("application/json");
		DEFAULT_PRODUCE_AND_CONSUME.add("application/xml");
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(DEFAULT_API_INFO).
				produces(DEFAULT_PRODUCE_AND_CONSUME).
				consumes(DEFAULT_PRODUCE_AND_CONSUME);
		
	}
}
