package com.techlabs.demoapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/hello1")
	public String getMessage() {
		return "helloWorld";
	}
	
	@GetMapping("/hello")
	public String getGreetings() {
		return "helloWorld";
	}
	

}
