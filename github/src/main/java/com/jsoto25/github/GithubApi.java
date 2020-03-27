package com.jsoto25.github;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class GithubApi {
	
	@CrossOrigin
    @RequestMapping("/github")
    String home() {
    	return "Ready for the git hub test...";
    }
}