package org.example.githubapiapp.service;

import org.example.githubapiapp.model.Branch;
import org.example.githubapiapp.model.BranchResponse;
import org.example.githubapiapp.model.GithubResponse;
import org.example.githubapiapp.model.Repo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoService {

    public static List<Repo> getAllRepos(String user) {

        return null;
    }

    //Gets all repos data of selected user deserialize data and map it on GithubResponses Returns list of GithubResponses
    public static GithubResponse[] getGithubRepos(String user) {
        String requestString = "https://api.github.com/users/"+user+"/repos";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GithubResponse[]> response = restTemplate.getForEntity(requestString, GithubResponse[].class);
        return response.getBody();
    }

    //Filters provided list of GithubResponses and excludes the forked repos Returns list of GithubResponses
    public static GithubResponse[] filterForks(GithubResponse[] allUserRepos) {
        List<GithubResponse> notforkedUserRepos = Arrays.stream(allUserRepos)
                .filter(userRepo -> !userRepo.isFork()).toList();
        return notforkedUserRepos.toArray(new GithubResponse[0]);
    }

    //Getting branches Returns list of Branches
    public static List<Branch> getBranches(String branchesURL) {
        String cleanURL = branchesURL.replace("{/branch}", "");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BranchResponse[]> response = restTemplate.getForEntity(cleanURL, BranchResponse[].class);
        BranchResponse[] branches = response.getBody();
        if (branches == null) {
            throw new RuntimeException("No branches found");
        }
        return Arrays.stream(branches).map(b->new Branch(b.getName(),b.getCommit().getSha())).toList();
    }

    //Maping Repos and Branches
    public static List<Repo> mapBranchesAndRepo(String user){
        List<Repo> finalRepos = new ArrayList<>();

        for (GithubResponse repo : filterForks(getGithubRepos(user))) {
            List<Branch> branches = getBranches(repo.getBranchesURL());

            Repo finalRepo = new Repo();
            finalRepo.setRepoName(repo.getName());
            finalRepo.setOwnerLogin(repo.getOwner().getLogin());
            finalRepo.setBranches(branches);

            finalRepos.add(finalRepo);
        }
        return finalRepos;
    }
    public static List<Repo> returnGithubRepos(String user){
        return mapBranchesAndRepo(user);
    }
}
