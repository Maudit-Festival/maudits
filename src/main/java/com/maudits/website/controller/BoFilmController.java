package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.BoFilmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}/film")
public class BoFilmController {
	private final BoFilmService boFilmService;

	@GetMapping("create/new")
	public String showFilmCreationCurrentEdition(@PathVariable Display edition, Model model) {
		model.addAttribute("form", boFilmService.createFilmForm());
		return "bo/film-create-or-edit";
	}

	@GetMapping("edit/{id}")
	public String showFilmEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boFilmService.findFilmFormFromId(id));
		return "bo/film-create-or-edit";
	}

	@PostMapping("save-edit")
	public String saveFilmEdition(@PathVariable Display edition, @Validated FilmForm form, Model model)
			throws IOException {
		boFilmService.saveFilm(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("delete")
	public String deleteFilm(@PathVariable Display edition, Long id, Model model) throws IOException {
		boFilmService.deleteFilm(id);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}
}
