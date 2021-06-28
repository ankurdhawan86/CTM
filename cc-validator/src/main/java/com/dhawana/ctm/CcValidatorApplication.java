package com.dhawana.ctm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class CcValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcValidatorApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage(("com.dhawana.ctm")))
				.build()
				.apiInfo(ccAppDetails());
	}

	private ApiInfo ccAppDetails() {
		return new ApiInfo(
				"Credit Card Validator API",
				"API for validating credit card number using Luhn's algorithm",
				"1.0",
				"Free to use",
				new Contact("Ankur Dhawan","https:github.com/dhawanankur86", "notToMention@anydomain.com"),
				"API Licence",
				"https:github.com/dhawanankur86",
				Collections.emptyList());
	}

}
