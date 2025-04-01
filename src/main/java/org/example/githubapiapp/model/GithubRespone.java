package org.example.githubapiapp.model;

public class GithubRespone {
    private String repoName;
    private Owner owner;
    private String branchesURL;
    private boolean fork;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getBranchesURL() {
        return branchesURL;
    }

    public void setBranchesURL(String branchesURL) {
        this.branchesURL = branchesURL;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public static class Owner {
        private String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }

}
