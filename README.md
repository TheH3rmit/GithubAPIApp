# GithubAPIApp
## Description
This application lists all github repositories of selected user which are not forks.

## Installation

## REST API

### Base URL
https://api/v1

### Endpoints
| Method | URL                                | Description                            |
|--------|------------------------------------|----------------------------------------|
| `GET`  | `/api/v1/{username}/repos`         | Retrieve all repos.                    |
| `GET`  | `/api/v1/{username}/reposnotforks` | Retrieve all repos that are not forks. |

### GET

#### Request
`GET /api/v1/{username}/reposnotforks`


#### Response


 