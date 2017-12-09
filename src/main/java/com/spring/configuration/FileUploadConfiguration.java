package com.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FileUploadConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**") 
				.addResourceLocations("file:/images/");

	}
}
//registry.addResourceHandler("/resources/**") : map for user type /resources .....* to access img
//.addResourceLocations("file:/images/"); location of images