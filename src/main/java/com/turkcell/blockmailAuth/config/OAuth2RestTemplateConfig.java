package com.turkcell.blockmailAuth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class OAuth2RestTemplateConfig  {
	
	@Bean("restTemplateBean")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<MediaType> mediaType = new ArrayList<>();
		mediaType.add(MediaType.APPLICATION_FORM_URLENCODED);
		mediaType.add(MediaType.APPLICATION_JSON);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(mediaType);
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}

}
