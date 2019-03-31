package com.atl.church.mms.com.atl.church.mms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.atl.church.mms.com.atl.church.mms.rest"))
				.paths(regex("/*.*"))
				.build()
				.apiInfo(metaData());
	}
	private ApiInfo metaData() {
		List<VendorExtension> extensions = new ArrayList<>();
		extensions.add(new StringVendorExtension("Tekalign Habtemichael","tekalignbeza@gmail.com"));
		extensions.add(new StringVendorExtension("Metasebiya Hailemariam","metyh2@gmail.com"));
		return new ApiInfoBuilder()
				.title("Member Management Service API")
				.description("MMS is application intended to register and manager members, record attendance and track contribution of each members. It also allow to schedule meetings and send notification to members of the church.")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.contact(new Contact("Metasebiya Hailemariam","https://www.linkedin.com/in/metasebiya-h","metyh2@gmail.com"))
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.extensions(extensions)
				.build();
	}
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}