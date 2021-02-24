package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.exception.WrongEditionException;
import com.maudits.website.domain.form.ContactMessageForm;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/next")
public class BoPreviewController {
	private final MauditService mauditService;

	@GetMapping("")
	public String previewNextEdition(Model model) {
		model.addAttribute("page", mauditService.makeHomeFilmRecap(DisplayEdition.NEXT));
		return "new/homepage";
	}

	@GetMapping("film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		try {
			model.addAttribute("page",
					mauditService.findFilmDetailPageDisplayerFromTextualId(DisplayEdition.NEXT, textualId));
			return "new/film-details";
		} catch (WrongEditionException e) {
			return "redirect:/bo/next/archive";
		}
	}

	@GetMapping("contact")
	public String showContactPage(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.NEXT));
		model.addAttribute("form", new ContactMessageForm());
		return "new/contact";
	}

	@GetMapping("archive")
	public String showArchive(Model model) {
		model.addAttribute("page", mauditService.makeArchivePage(DisplayEdition.NEXT));
		return "new/archive";
	}
	@GetMapping("a-propos-du-maudit-festival")
	public String showAbout(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.NEXT));
		return "new/about";
	}

	@GetMapping("editions-precedentes/avant-le-maudit-festival")
	public String showHistory(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.NEXT));
		return "new/history";
	}

	@GetMapping("editions-precedentes/{editionCode}")
	public String showEdition(@PathVariable String editionCode, Model model) {
		model.addAttribute("page", mauditService.makePreviousEditionPage(editionCode, DisplayEdition.NEXT));
		return "new/previous-edition";
	}
}
