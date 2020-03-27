package com.jsoto25.github;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Home {
	
	@CrossOrigin
    @RequestMapping("/")
    String home() {
    	return "REST Test GitHub  API Running ok... ";
    }
}