package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo")
public class BoHomepageController {
	@GetMapping("")
	public String showHomepage() {
		return "bo/homepage";
	}
}
