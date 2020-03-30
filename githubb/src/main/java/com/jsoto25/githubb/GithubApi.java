package com.jsoto25.githubb;

import javax.websocket.server.PathParam;

import com.fasterxml.jackson.core.sym.Name;

//import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/github")
@RestController
@EnableAutoConfiguration
public class GithubApi {

  private static String githubToken = "4c97eac7fa51ffd034a223f566b8ed7e045eca9b";

  @CrossOrigin
  @RequestMapping("/")
  String home() {
    return "Ready for the git hub test...";
  }

  @GetMapping("/repos")
  String getListRepos(@RequestParam String gitHubUser) {

    String result = "Getting repos list of: " + gitHubUser;

    return result;

  }

  @PostMapping("/create")

  String newEmployee(@RequestBody Repo newRepo) {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + githubToken);
    headers.add("Content-Type", "application/json");
    headers.add("Content-Type", "application/json");
    // String jsonRepository = new Gson().toJson(newRepo);
    String jsonRepository = "new Gson().toJson(newRepo)";
    HttpEntity<String> entity = new HttpEntity<String>(jsonRepository, headers);
    String result = restTemplate.postForObject("https://api.github.com/user/repos", entity, String.class);

    return result;
  }

}