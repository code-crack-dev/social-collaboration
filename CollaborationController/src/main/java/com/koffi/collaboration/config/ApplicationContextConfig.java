package com.koffi.collaboration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.koffi"})
public class ApplicationContextConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;	
	}
	
	
	public  void configureDefaultServerHandlibng(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonMultipartResolver()
	{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(30000000);
		multipartResolver.setMaxInMemorySize(1048576);
		return multipartResolver;
	}

}
