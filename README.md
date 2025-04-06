# GithubAPIApp
## Description
This Spring Boot application lists all public GitHub repositories of a given user, excluding forks. 
It also returns all branches within those repositories and the last commit SHA per branch.

## Installation

1. Clone the repository:
```bash
    git clone https://github.com/your-username/GithubAPIApp.git
    cd GithubAPIApp
```
2. Build and run the project:
```bash
    ./mvnw spring-boot:run
```

## REST API

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

|Method|URL|Description|
|---|---|---|
|GET|`/api/v1/{username}`|Verifies if the GitHub user exists.|
|GET|`/api/v1/{username}/reposnotforks`|Returns non-fork public repos + branches + SHAs.|

### GET `/api/v1/{username}/reposnotforks`

#### Successful Response (HTTP 200)

Returns a list of repositories (excluding forks) with branch info.

```json
[
  {
    "repoName": "repo-name",
    "ownerLogin": "username",
    "branches": [
      {
        "branchName": "main",
        "sha": "123"
      },
      {
        "branchName": "dev",
        "sha": "123"
      }
    ]
  }
]
```

#### Error Response (User Not Found – HTTP 404)

```json
{
  "status": 404,
  "message": "User does not exist"
}
```


## Testing

To run integration tests:

```bash
./mvnw test
```

Tests include:

- Existing user with repos
- Nonexistent user
 