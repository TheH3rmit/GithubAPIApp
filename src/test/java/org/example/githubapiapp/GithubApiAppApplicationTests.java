package org.example.githubapiapp;

import org.example.githubapiapp.handler.ResponseHandler;
import org.example.githubapiapp.model.Branch;
import org.example.githubapiapp.model.Repo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubApiAppApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/v1";
    }

    @Test
    void contextLoads() {
    }
    @Test
    void userExists() {
        ResponseEntity<String> githubResult = restTemplate.getForEntity("https://api.github.com/users/TheH3rmit", String.class);
        ResponseEntity<String> myResult = restTemplate.getForEntity(baseUrl+"/TheH3rmit", String.class);

        assertEquals (githubResult.getStatusCode(), myResult.getStatusCode());
    }
    @Test
    void userNotExists() {
        ResponseEntity<String> githubResult = restTemplate.getForEntity("https://api.github.com/users/!", String.class);
        ResponseEntity<String> myResult = restTemplate.getForEntity(baseUrl+"/!", String.class);

        assertEquals (githubResult.getStatusCode(), myResult.getStatusCode());

    }
    @Test
    void userFoundReposNotForks() {
        String username = "TheH3rmit";
        String url = baseUrl + "/" + username+"/reposnotforks";

        ResponseEntity<Repo[]> response = restTemplate.getForEntity(url, Repo[].class);


        assertEquals(HttpStatus.OK, response.getStatusCode());

        Repo[] repos = response.getBody();
        assertNotNull(repos);
        assertTrue(repos.length > 0);


        Repo repo = repos[0];
        assertNotNull(repo.getRepoName());
        assertNotNull(repo.getOwnerLogin());
        assertNotNull(repo.getBranches());

        if (!repo.getBranches().isEmpty()) {
            Branch branch = repo.getBranches().get(0);
            assertNotNull(branch.getBranchName());
            assertNotNull(branch.getSha());
        }
    }
    @Test
    void userNotFoundReposNotForks() {
        String username = "!";
        String url = baseUrl + "/" + username +"/reposnotforks";
        ResponseEntity<String> myResult = restTemplate.getForEntity(baseUrl+"/TheH3rmit", String.class);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(response.getBody(), ResponseHandler.generateResponse(HttpStatus.NOT_FOUND,"User does not exist").toString() );

    }

}
