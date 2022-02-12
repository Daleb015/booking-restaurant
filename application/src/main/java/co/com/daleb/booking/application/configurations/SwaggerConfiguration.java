package co.com.daleb.booking.application.configurations;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

  @Bean
  Docket swagger() {
    return new Docket(DocumentationType.OAS_30)
      .useDefaultResponseMessages(false)
      .produces(Stream.of("application/xml", "application/json").collect(Collectors.toSet()))
      .select()
      .apis(RequestHandlerSelectors.basePackage("co.com.daleb.booking.application.controllers"))
      .build()
      .protocols(Stream.of("http", "https").collect(Collectors.toSet()));
  }
}
