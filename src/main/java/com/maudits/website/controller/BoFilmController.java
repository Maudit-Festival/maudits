package com.maudits.website.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.ExtraEventForm;
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
		model.addAttribute("currentExtraEvents", boService.findCurrentExtraEvents());
		model.addAttribute("pastExtraEvents", boService.findPastExtraEvents());
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
	public String saveFilmEdition(@Valid FilmForm form, Model model) throws IOException {
		boService.saveFilm(form);
		return "redirect:/bo";
	}

	@PostMapping("film/delete")
	public String deleteFilm(Long id, Model model) throws IOException {
		boService.deleteFilm(id);
		return "redirect:/bo";
	}

	@GetMapping("extraevent/create")
	public String showExtraEventCreation(Model model) {
		model.addAttribute("form", boService.createExtraEvent());
		model.addAttribute("extraEvent", true);
		return "bo/film-create-or-edit";
	}

	@GetMapping("extraevent/edit/{id}")
	public String showExtraEventEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boService.findExtraEventFormFromId(id));
		model.addAttribute("extraEvent", true);
		return "bo/film-create-or-edit";
	}

	@PostMapping("extraevent/save-edit")
	public String saveExtraEventEdition(@Valid ExtraEventForm form, Model model) throws IOException {
		boService.saveExtraEvent(form);
		return "redirect:/bo";
	}
	
	@PostMapping("extraevent/delete")
	public String deleteExtraEvent(Long id, Model model) throws IOException {
		boService.deleteExtraEvent(id);
		return "redirect:/bo";
	}

}
