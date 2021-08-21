package com.example.erafmak;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainEntryController {

	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
}
