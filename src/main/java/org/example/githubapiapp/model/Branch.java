package org.example.githubapiapp.model;

public class Branch {
    private String branchName;
    private String sha;

    public Branch(String name, String sha) {
        this.branchName = name;
        this.sha = sha;
    }
    public Branch() {}


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
