package com.jsoto25.githubb;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Home {
	
	@CrossOrigin
    @RequestMapping("/")
    String home() {
    	return "REST 2 Test GitHub  API Running ok... ";
    }
}