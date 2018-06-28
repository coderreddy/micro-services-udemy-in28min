package com.kris.udemy.microservicesspnewlearn;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
public class MicroServicesSpNewLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesSpNewLearnApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver()
	{
		AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
}
