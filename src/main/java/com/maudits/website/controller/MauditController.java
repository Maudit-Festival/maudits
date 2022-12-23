package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.exception.WrongEditionException;
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
		model.addAttribute("page", mauditService.makeHomeFilmRecap(DisplayEdition.CURRENT));
		return "homepage";
	}

	@GetMapping("/film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		try {
			model.addAttribute("page",
					mauditService.findFilmDetailPageDisplayerFromTextualId(DisplayEdition.CURRENT, textualId));
			return "film-details";
		} catch (WrongEditionException e) {
			return "redirect:/";
		}
	}

	@GetMapping("/contact")
	public String showContactPage(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
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
			model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
			model.addAttribute("success", true);
			model.addAttribute("form", form);
			return "contact";
		} else {
			model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
			model.addAttribute("error", true);
			model.addAttribute("form", form);
			return "contact";
		}
	}

	@GetMapping("archive")
	public String showArchive(Model model) {
		model.addAttribute("page", mauditService.makeArchivePage(DisplayEdition.CURRENT));
		return "archive";
	}

	@GetMapping("a-propos-du-maudit-festival")
	public String showAbout(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
		return "about";
	}

	@GetMapping("editions-precedentes/avant-le-maudit-festival")
	public String showHistory(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
		return "history";
	}

	@GetMapping("editions-precedentes/{editionName}")
	public String showEdition(@PathVariable String editionName, Model model) {
		model.addAttribute("page", mauditService.makePreviousEditionPage(editionName, DisplayEdition.CURRENT));
		return "previous-edition";
	}

}
