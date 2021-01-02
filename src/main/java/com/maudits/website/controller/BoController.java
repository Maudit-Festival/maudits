package com.maudits.website.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.ContactMessageForm;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.BoFilmService;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo")
public class BoController {
	private final BoFilmService boService;
	private final MauditService mauditService;

	@GetMapping("")
	public String boHomepage(Model model) {
		model.addAttribute("currentFilms", boService.findCurrentFilms());
		model.addAttribute("nextFilms", boService.findNextFilms());
		return "bo/homepage";
	}

	@GetMapping("/next")
	public String previewNextEdition(Model model) {
		model.addAttribute("this", mauditService.makeHomeFilmRecapNextEdition());
		return "new/homepage";
	}

	@GetMapping("/next/film/{textualId}")
	public String showFilm(@PathVariable String textualId, Model model) {
		model.addAttribute("filmDisplayer", mauditService.findFilmDisplayerFromTextualId(textualId));
		return "new/film-details";
	}

	@GetMapping("/next/contact")
	public String showContactPage(Model model) {
		model.addAttribute("form", new ContactMessageForm());
		return "new/contact";
	}

	@GetMapping("film/edit/{id}")
	public String showFilmEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boService.findFilmFormFromId(id));
		return "bo/film-create-or-edit";
	}

	@GetMapping("film/create-next-edition")
	public String showFilmCreationNextEdition(Model model) {
		model.addAttribute("form", boService.createFilmFormNextEdition());
		return "bo/film-create-or-edit";
	}

	@GetMapping("film/create-current-edition")
	public String showFilmCreationCurrentEdition(Model model) {
		model.addAttribute("form", boService.createFilmFormCurrentEdition());
		return "bo/film-create-or-edit";
	}

	@PostMapping("film/save-edit")
	public String saveFilmEdition(@Valid FilmForm form, Model model) throws IOException {
		boService.saveFilm(form);
		return "redirect:/bo";
	}
}
