package com.example.presentation.resources;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configureApiVersioning(ApiVersionConfigurer configurer) {
//		configurer.usePathSegment(2).setDefaultVersion("0");
		configurer.useRequestHeader("x-api-version").setDefaultVersion("0");
		configurer.useQueryParam("version").setDefaultVersion("0");
//		configurer.useMediaTypeParameter().setDefaultVersion("0");
	}
}