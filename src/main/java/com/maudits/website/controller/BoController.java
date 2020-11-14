package com.maudits.website.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.BoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo")
public class BoController {
	private final BoService boService;

	@GetMapping("")
	public String boHomepage(Model model) {
		model.addAttribute("films", boService.findAllFilms());
		return "bo/homepage";
	}

	@GetMapping("film/edit/{id}")
	public String showFilmEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boService.findFilmFormFromId(id));
		return "bo/film-create-or-edit";
	}

	@GetMapping("film/create")
	public String showFilmCreation(Model model) {
		model.addAttribute("form", boService.createFilmForm());
		return "bo/film-create-or-edit";
	}

	@PostMapping("film/save-edit")
	public String saveFilmEdition(@Valid FilmForm form, Model model) throws IOException {
		boService.saveFilm(form);
		return "redirect:/bo";
	}
}
