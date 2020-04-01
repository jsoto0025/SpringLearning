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

  private final String githubToken = "4c97eac7fa51ffd034a223f566b8ed7e045eca9b";
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

  @GetMapping("/reposfinal")
  String getListReposFinal(@RequestParam final String gitHubUser) throws JsonMappingException, JsonProcessingException {

    String repositoryListsUrl = this.gitHubApiUrl + "/users/" + gitHubUser + "/repos";

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(repositoryListsUrl, String.class);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.getBody());

    return root.toPrettyString();
  }

  @PostMapping("/create")

  String newRepository(@RequestBody Repo newRepo) throws JsonProcessingException {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic anNvdG8wMDI1OmQ0MmU5YjUyNTU2MDJiOWIwYTQ3YThmN2I4YTUzOWRiZWFiMzE4NzI=");
    headers.add("Content-Type", "application/json");
    ObjectMapper mapper = new ObjectMapper();
    String jsonRepository = mapper.writeValueAsString(newRepo);
    HttpEntity<String> entity = new HttpEntity<String>(jsonRepository, headers);
    String response = restTemplate.postForObject(this.gitHubApiUrl + "user/repos", entity, String.class);
    ObjectMapper resultMapper = new ObjectMapper();
    JsonNode root = resultMapper.readTree(response);

    return root.toPrettyString();

  }

}