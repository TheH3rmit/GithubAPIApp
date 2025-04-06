package org.example.githubapiapp.controller;

import org.example.githubapiapp.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.githubapiapp.handler.ResponseHandler;
import org.example.githubapiapp.handler.Helper;

@RestController
@RequestMapping("/api/v1/")
public class RepoController {
    @Autowired
    private Helper helper;
    @Autowired
    RepoService repoService;
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/{username}/reposnotforks")
    public ResponseEntity<?> reposnotforks(@PathVariable String username) {
        HttpStatusCode statusCode = helper.userExists(username);
        if (statusCode != HttpStatus.OK) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "User does not exist");
        }
        return ResponseEntity.ok(repoService.returnGithubRepos(username));
    }

    @GetMapping("/{username}")
    public HttpStatusCode getRepo(@PathVariable String username) {
        return helper.userExists(username);
    }
}
