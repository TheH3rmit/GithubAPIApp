package org.example.githubapiapp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class Helper {
    @Autowired
    private RestTemplate restTemplate;

    public HttpStatusCode userExists(String username) {
        String userCheck = "https://api.github.com/users/" + username;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(userCheck, String.class);
            return response.getStatusCode();
        } catch (HttpClientErrorException e) {
            return e.getStatusCode();
        }
    }
}

