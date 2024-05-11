package com.harish.demo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Bikes API", version = "v1",description = "A sample API for managing bikes",contact = @Contact(email = "contact@example.com", name = "API Support")),servers = @Server(url = "http://localhost:8080"))
public class OpenApi3Config {

}
