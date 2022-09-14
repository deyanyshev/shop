package com.internship.site;

import com.internship.site.config.MyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class SiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
}
