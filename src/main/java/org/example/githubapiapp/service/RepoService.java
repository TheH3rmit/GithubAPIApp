package org.example.githubapiapp.service;

import org.example.githubapiapp.model.Branch;
import org.example.githubapiapp.model.BranchResponse;
import org.example.githubapiapp.model.GithubResponse;
import org.example.githubapiapp.model.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RepoService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Repo> getAllRepos(String user) {

        return null;
    }

    //Gets all repos data of selected user deserialize data and map it on GithubResponses Returns list of GithubResponses
    public GithubResponse[] getGithubRepos(String user) {
        String requestString = "https://api.github.com/users/"+user+"/repos";
        ResponseEntity<GithubResponse[]> response = restTemplate.getForEntity(requestString, GithubResponse[].class);
        return response.getBody();
    }

    //Filters provided list of GithubResponses and excludes the forked repos Returns list of GithubResponses
    public GithubResponse[] filterForks(GithubResponse[] allUserRepos) {
        List<GithubResponse> notforkedUserRepos = Arrays.stream(allUserRepos)
                .filter(userRepo -> !userRepo.isFork()).toList();
        return notforkedUserRepos.toArray(new GithubResponse[0]);
    }

    //Getting branches Returns list of Branches
    public List<Branch> getBranches(String branchesURL) {
        String cleanURL = branchesURL.replace("{/branch}", "");
        ResponseEntity<BranchResponse[]> response = restTemplate.getForEntity(cleanURL, BranchResponse[].class);
        BranchResponse[] branches = response.getBody();
        if (branches == null) {
            throw new RuntimeException("No branches found");
        }
        return Arrays.stream(branches).map(b->new Branch(b.getName(),b.getCommit().getSha())).toList();
    }

    //Maping Repos and Branches
    public List<Repo> mapBranchesAndRepo(String user){
        List<Repo> finalRepos = new ArrayList<>();

        for (GithubResponse repo : filterForks(getGithubRepos(user))) {
            List<Branch> branches = getBranches(repo.getBranches_url());

            Repo finalRepo = new Repo();
            finalRepo.setRepoName(repo.getName());
            finalRepo.setOwnerLogin(repo.getOwner().getLogin());
            finalRepo.setBranches(branches);

            finalRepos.add(finalRepo);
        }
        return finalRepos;
    }
    public List<Repo> returnGithubRepos(String user){
        return mapBranchesAndRepo(user);
    }
}
