package com.jsoto25.github;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/github")
@RestController
@EnableAutoConfiguration
public class GithubApi {
	
	@CrossOrigin
    @RequestMapping("/")
    String home() {
    	return "Ready for the git hub test...";
    }

    @PostMapping("/create")
    String newEmployee(@RequestBody Repo newRepo) {

      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders(); 
      headers.add("Authorization", "Bearer 3dfa18507017ae0fe14c664a13a968f198c21d0c");
      headers.add("Content-Type", "application/json");
      headers.add("Content-Type", "application/json");
      String jsonRepository = new Gson().toJson(newRepo);
      HttpEntity<String> entity = new HttpEntity<String>(jsonRepository, headers);
      String result = restTemplate.postForObject("https://api.github.com/user/repos", entity, String.class);

      return result;
    }

}