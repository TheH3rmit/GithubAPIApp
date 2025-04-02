package org.example.githubapiapp.model;

public class GithubResponse {
    private String name;
    private Owner owner;
    private String branchesURL;
    private boolean fork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
