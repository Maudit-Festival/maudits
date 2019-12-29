package com.maudits.website.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.maudits.website.domain.form.ContactMessageForm;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.ContactService;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MauditController {
	private final MauditService mauditService;
	private final ContactService contactService;

	@GetMapping("/")
	public String showHomePage(Model model) {
		model.addAttribute("this", mauditService.makeHomeFilmRecap());
		return "homepage";
	}

	@GetMapping("/film/{id}")
	public String showFilm(@PathVariable Long id, Model model) {
		model.addAttribute("filmDisplayer", mauditService.findFilmDisplayerFromId(id));
		return "film-details";
	}

	@GetMapping("/contact")
	public String showContactPage(Model model) {
		model.addAttribute("form", new ContactMessageForm());
		return "contact";
	}

	@PostMapping("/contact")
	public String sendEmail(ContactMessageForm form, Model model) {
		contactService.sendEmail(form);
		return "redirect:/contact";
	}

	@GetMapping("bo/film/edit/{id}")
	public String showFilmEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", mauditService.findFilmFormFromId(id));
		return "film-create-or-edit";
	}

	@PostMapping("bo/film/save-edit")
	public String saveFilmEdition(@PathVariable Long id, @Valid FilmForm form, Model model) {
		mauditService.saveFilm(form);
		return "redirect:/";
	}
}
