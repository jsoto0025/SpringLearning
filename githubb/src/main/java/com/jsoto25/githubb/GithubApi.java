package com.jsoto25.githubb;

import javax.websocket.server.PathParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    String result = "Getting repos list of: " + gitHubUser;
    String repositoryListsUrl = this.gitHubApiUrl + "/users/" + gitHubUser + "/repos";
    /*
     * RestTemplate restTemplate = new RestTemplate(); HttpHeaders headers = new
     * HttpHeaders(); headers.add("Authorization", "Bearer " + githubToken);
     * headers.add("Content-Type", "application/json"); headers.add("Content-Type",
     * "application/json"); // String jsonRepository = new Gson().toJson(newRepo);
     * String jsonRepository = "new Gson().toJson(newRepo)"; HttpEntity<String>
     * entity = new HttpEntity<String>(jsonRepository, headers);
     * ResponseEntity<String> resultb =
     * restTemplate.getForEntity("https://api.github.com/user/repos", entity,
     * String.class);
     * 
     * return result;
     */
    final RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> response = restTemplate.getForEntity(repositoryListsUrl, String.class);
    // assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.getBody());
    //final JsonNode name = root.path("name");

    return root.toPrettyString();
  }

  @PostMapping("/create")

  String newEmployee(@RequestBody final Repo newRepo) {

    final RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + githubToken);
    headers.add("Content-Type", "application/json");
    headers.add("Content-Type", "application/json");
    // String jsonRepository = new Gson().toJson(newRepo);
    final String jsonRepository = "new Gson().toJson(newRepo)";
    final HttpEntity<String> entity = new HttpEntity<String>(jsonRepository, headers);
    final String result = restTemplate.postForObject("https://api.github.com/user/repos", entity, String.class);

    return result;
  }

}