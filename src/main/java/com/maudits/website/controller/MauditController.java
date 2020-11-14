package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maudits.website.domain.form.ContactMessageForm;
import com.maudits.website.service.CaptchaService;
import com.maudits.website.service.ContactService;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MauditController {
	private final MauditService mauditService;
	private final CaptchaService captchaService;
	private final ContactService contactService;

	@GetMapping("/")
	public String showHomePage(Model model) {
		model.addAttribute("this", mauditService.makeHomeFilmRecap());
		return "homepage";
	}

	@GetMapping("/film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		model.addAttribute("filmDisplayer", mauditService.findFilmDisplayerFromTextualId(textualId));
		return "film-details";
	}

	@GetMapping("/contact")
	public String showContactPage(Model model) {
		model.addAttribute("form", new ContactMessageForm());
		return "contact";
	}

	@PostMapping("/contact")
	public String sendEmail(ContactMessageForm form, @RequestParam("g-recaptcha-response") String reCaptchaResponse,
			Model model) {
		boolean success = captchaService.checkResponse(reCaptchaResponse);
		if (success) {
			success &= contactService.sendEmail(form);
		}
		if (success) {
			model.addAttribute("success", true);
			model.addAttribute("form", form);
			return "contact";
		} else {
			model.addAttribute("error", true);
			model.addAttribute("form", form);
			return "contact";
		}
	}
}
