package th.co.nuttida.tellermgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("th.co.nuttida.docmgmt.controller")
public class SwaggerConfiguration {

	@Bean
	public Docket selectApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("th.co.nuttida.docmgmt.controller"))
				.paths(Predicates.not(PathSelectors.regex("/error"))).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		String description = "Document Management Application";
		return new ApiInfoBuilder().title("Document Management Application").description(description)
				.termsOfServiceUrl("github").license("Nuttida Wongsomsak").version("1.0").build();
	}

}