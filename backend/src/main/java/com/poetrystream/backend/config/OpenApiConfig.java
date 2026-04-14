package com.poetrystream.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PoetryStream API")
                        .version("1.0")
                        .description("Backend service for streaming poetry content"))
                .servers(List.of(
                        new Server()
                                .url("/api")
                                .description("Production API (via reverse proxy)"),
                        new Server().url("http://localhost:8080").description("Local Dev"),
                        new Server().url(frontendUrl).description("Frontend Reference")
                ));
    }
}