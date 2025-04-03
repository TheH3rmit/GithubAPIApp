package org.example.githubapiapp.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Helper {
    //Checks if the user exits Returns HttpStatusCode
    public static HttpStatusCode userExists(String username) {
        String userCheck = "https://api.github.com/users/"+username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(userCheck, String.class);
        return response.getStatusCode();
    }
}
