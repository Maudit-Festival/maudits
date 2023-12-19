package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.ExtraEventForm;
import com.maudits.website.service.BoFilmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/extraevent")
public class BoExtraEventController {
	private final BoFilmService boService;

	@GetMapping("dashboard")
	public String showExtraEvents(Model model) {
		model.addAttribute("currentExtraEvents", boService.findCurrentExtraEvents());
		model.addAttribute("pastExtraEvents", boService.findPastExtraEvents());
		return "bo/extra-event-dashboard";
	}

	@GetMapping("create/new")
	public String showExtraEventCreation(Model model) {
		model.addAttribute("form", boService.createExtraEvent());
		model.addAttribute("extraEvent", true);
		return "bo/film-create-or-edit";
	}

	@GetMapping("edit/{id}")
	public String showExtraEventEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boService.findExtraEventFormFromId(id));
		model.addAttribute("extraEvent", true);
		return "bo/film-create-or-edit";
	}

	@PostMapping("save-edit")
	public String saveExtraEventEdition(@Validated ExtraEventForm form, Model model) throws IOException {
		boService.saveExtraEvent(form);
		return "redirect:/bo/extraevent/dashboard";
	}

	@PostMapping("delete")
	public String deleteExtraEvent(Long id, Model model) throws IOException {
		boService.deleteExtraEvent(id);
		return "redirect:/bo/extraevent/dashboard";
	}

}
