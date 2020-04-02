package com.jsoto25.githubb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/github")
@RestController
@EnableAutoConfiguration
public class GithubApi {

  private final String githubToken = "[GITHUB TOKEN]";
  private final String gitHubApiUrl = "https://api.github.com/";

  @CrossOrigin
  @RequestMapping("/")
  String home() {
    return "Ready for the git hub test...";
  }

  @GetMapping("/repos")
  String getListRepos(@RequestParam final String gitHubUser) {

    final String result = "Getting repos list of: " + gitHubUser;

    return result;

  }

  /**
   * REST Method used to get the GitHub repository list from a GitHub registered
   * user
   * 
   * @param gitHubUser GitHub registered user
   * @return JSON Formated String with GitHb repository list from GitHub registered user.
   * @throws JsonMappingException
   * @throws JsonProcessingException
   */
  @GetMapping("/reposfinal")
  String getListReposFinal(@RequestParam final String gitHubUser) throws JsonMappingException, JsonProcessingException {

    String userRepositoryList;
    RestTemplate restTemplate;
    ResponseEntity<String> response;
    ObjectMapper jsonMapper;
    JsonNode jsonResponseRootNode;

    userRepositoryList = this.gitHubApiUrl + "/users/" + gitHubUser + "/repos";
    restTemplate = new RestTemplate();
    response = restTemplate.getForEntity(userRepositoryList, String.class);
    jsonMapper = new ObjectMapper();
    jsonResponseRootNode = jsonMapper.readTree(response.getBody());
    return jsonResponseRootNode.toPrettyString();
  }

  @PostMapping("/createfinal")

  /**
   * REST Method for GitHub repository creation, using Bearer Authentication
   * 
   * @param newRepo POJO GitHub repository object
   * @return Text formated in JSON with the GitHub API v3 resoult
   * @throws JsonProcessingException
   */
  String newRepositoryBearer(@RequestBody Repo newRepo) throws JsonProcessingException {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers;
    ObjectMapper mapper;
    String jsonRepository;
    ObjectMapper resultMapper;
    String response;
    JsonNode jsonRootResponse;
    HttpEntity<String> entity;

    headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + this.githubToken );
    headers.add("Content-Type", "application/json");

    mapper = new ObjectMapper();
    jsonRepository = mapper.writeValueAsString(newRepo);
    entity = new HttpEntity<String>(jsonRepository, headers);
    response = restTemplate.postForObject(this.gitHubApiUrl + "user/repos", entity, String.class);
    resultMapper = new ObjectMapper();
    jsonRootResponse = resultMapper.readTree(response);

    return jsonRootResponse.toPrettyString();

  }

}