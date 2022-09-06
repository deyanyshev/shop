package com.internship.site;

import com.internship.site.config.MyConfiguration;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Arrays;


@SpringBootApplication
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
}
