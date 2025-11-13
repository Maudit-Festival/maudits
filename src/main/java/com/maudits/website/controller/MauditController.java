package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maudits.website.domain.Display;
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
		model.addAttribute("page", mauditService.makeHomeFilmRecap(Display.CURRENT));
		return "homepage";
	}

	@GetMapping("editions-precedentes/{editionName}")
	public String showPreviousEditionHomePage(@PathVariable String editionName, Model model) {
		model.addAttribute("page", mauditService.makeHomeFilmRecap(Display.CURRENT, editionName));
		return "homepage";
	}

	@GetMapping("/film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		try {
			model.addAttribute("page",
					mauditService.findFilmDetailPageDisplayerFromTextualId(Display.CURRENT, textualId));
			return "film-details";
		} catch (WrongEditionException e) {
			return "redirect:/";
		}
	}

	@GetMapping("/contact")
	public String showContactPage(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
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
			model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
			model.addAttribute("success", true);
			model.addAttribute("form", form);
			return "contact";
		} else {
			model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
			model.addAttribute("error", true);
			model.addAttribute("form", form);
			return "contact";
		}
	}

	@GetMapping("a-propos-du-maudit-festival")
	public String showAbout(Model model) {
		model.addAttribute("page", mauditService.makeAboutPageDisplayer(Display.CURRENT));
		return "about";
	}

	@GetMapping("editions-precedentes/avant-le-maudit-festival")
	public String showHistory(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
		return "history";
	}

	@GetMapping("admin/history")
	public String showHistoryUpcoming(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
		return "history-upcoming";
	}

	@GetMapping("booth")
	public String showBoothLogin(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
		return "booth-connect-form";
	}

	@PostMapping("booth")
	public String showBoothPictures(String password, Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.CURRENT));
		model.addAttribute("pictures", mauditService.findBoothPictures(password));
		return "booth-pictures";
	}
}
