package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.exception.WrongEditionException;
import com.maudits.website.domain.form.ContactMessageForm;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/next")
public class BoPreviewController {
	private final MauditService mauditService;

	@GetMapping("")
	public String previewNextEdition(Model model) {
		model.addAttribute("page", mauditService.makeHomeFilmRecap(Display.NEXT));
		return "homepage";
	}

	@GetMapping("editions-precedentes/{editionName}")
	public String showPreviousEditionHomePageP(@PathVariable String editionName, Model model) {
		model.addAttribute("page", mauditService.makeHomeFilmRecap(Display.NEXT, editionName));
		return "homepage";
	}

	@GetMapping("film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		try {
			model.addAttribute("page", mauditService.findFilmDetailPageDisplayerFromTextualId(Display.NEXT, textualId));
			return "film-details";
		} catch (WrongEditionException e) {
			return "redirect:/admin/next";
		}
	}

	@GetMapping("contact")
	public String showContactPage(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.NEXT));
		model.addAttribute("form", new ContactMessageForm());
		return "contact";
	}

	@GetMapping("a-propos-du-maudit-festival")
	public String showAbout(Model model) {
		model.addAttribute("page", mauditService.makeAboutPageDisplayer(Display.NEXT));
		return "about";
	}

	@GetMapping("editions-precedentes/{editionName}/a-propos-du-maudit-festival")
	public String showAboutPreviousEdition(@PathVariable String editionName, Model model) {
		model.addAttribute("page", mauditService.makeAboutPageDisplayer(Display.NEXT, editionName));
		return "about";
	}

	@GetMapping("editions-precedentes/avant-le-maudit-festival")
	public String showHistory(Model model) {
		model.addAttribute("page", mauditService.makeFrontPage(Display.NEXT));
		return "history";
	}

}
