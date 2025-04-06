package org.example.githubapiapp;

import org.example.githubapiapp.model.Branch;
import org.example.githubapiapp.model.Repo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(true);
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() {
        String url = baseUrl + "/this_user_does_not_exist_abc123/reposnotforks";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(404, body.get("status"));
        assertEquals("User does not exist", body.get("message"));
    }

    @Test
    void shouldReturnReposForExistingUser() {
        String url = baseUrl + "/TheH3rmit/reposnotforks";

        ResponseEntity<Repo[]> response = restTemplate.getForEntity(url, Repo[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Repo[] repos = response.getBody();
        assertNotNull(repos);
        assertTrue(repos.length > 0);

        for (Repo repo : repos) {
            assertNotNull(repo.getRepoName());
            assertNotNull(repo.getOwnerLogin());
            assertNotNull(repo.getBranches());

            for (Branch branch : repo.getBranches()) {
                assertNotNull(branch.getBranchName());
                assertNotNull(branch.getSha());
            }
        }
    }

}
