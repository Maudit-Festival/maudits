package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.BoFilmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo")
public class BoFilmController {
	private final BoFilmService boService;

	@GetMapping("")
	public String boHomepage(Model model) {
		model.addAttribute("currentFilms", boService.findCurrentFilms());
		model.addAttribute("nextFilms", boService.findNextFilms());
		return "bo/homepage";
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
	public String saveFilmEdition(FilmForm form, Model model) throws IOException {
		boService.saveFilm(form);
		return "redirect:/bo";
	}
}
