package org.example.githubapiapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubApiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubApiAppApplication.class, args);
    }

    @Value("${github.token:}")
    private String githubToken;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .additionalInterceptors((request, body, execution) -> {
                    if (githubToken != null && !githubToken.isEmpty()) {
                        request.getHeaders().set("Authorization", "Bearer " + githubToken);
                    }
                    return execution.execute(request, body);
                })
                .build();
    }


}
