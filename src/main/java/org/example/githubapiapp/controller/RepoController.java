package org.example.githubapiapp.controller;

import org.example.githubapiapp.model.Repo;
import org.example.githubapiapp.service.RepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        return ResponseEntity.ok(RepoService.returnGithubRepos(username));
    }
}
