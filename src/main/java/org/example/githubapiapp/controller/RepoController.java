package org.example.githubapiapp.controller;

import org.example.githubapiapp.model.Repo;
import org.example.githubapiapp.service.RepoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.example.githubapiapp.handler.ResponseHandler;
import org.example.githubapiapp.handler.Helper;


import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RepoController {
    RepoService repoService;
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/{username}/reposnotforks")
    public ResponseEntity<List<Repo>> reposnotforks(@PathVariable String username) {
        HttpStatusCode statusCode = Helper.userExists(username);
        if(statusCode != HttpStatus.OK) {
            throw new HttpClientErrorException(ResponseHandler.generateResponse(statusCode,"User does not exist").getStatusCode());
        }
        return ResponseEntity.ok(RepoService.returnGithubRepos(username));
    }

    @GetMapping("/{username}")
    public HttpStatusCode getRepo(@PathVariable String username) {
        return Helper.userExists(username);
    }
}
