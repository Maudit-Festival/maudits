package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.ExtraEventForm;
import com.maudits.website.service.BoFilmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/extraevent")
public class BoExtraEventController {
	private final BoFilmService boService;

	@ModelAttribute
	public void setAction(Model model) {
		model.addAttribute("actionBaseUrl", "/admin/extraevent");
		model.addAttribute("back", "/admin/extraevent/dashboard");
	}

	@GetMapping("dashboard")
	public String showExtraEvents(Model model) {
		model.addAttribute("currentExtraEvents", boService.findCurrentExtraEvents());
		model.addAttribute("pastExtraEvents", boService.findPastExtraEvents());
		return "admin/extra-event-dashboard";
	}

	@GetMapping("create/new")
	public String showExtraEventCreation(Model model) {
		model.addAttribute("form", boService.createExtraEvent());
		model.addAttribute("extraEvent", true);
		return "admin/film-create-or-edit";
	}

	@GetMapping("edit/{id}")
	public String showExtraEventEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boService.findExtraEventFormFromId(id));
		model.addAttribute("extraEvent", true);
		return "admin/film-create-or-edit";
	}

	@PostMapping("save-edit-publish")
	public String saveExtraEventEdition(@Validated @ModelAttribute("form") ExtraEventForm form,
			BindingResult bindingResult, Model model) throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("extraEvent", true);
			return "admin/film-create-or-edit";
		}
		boService.saveExtraEvent(form);
		return "redirect:/admin/extraevent/dashboard";
	}

	@PostMapping("delete")
	public String deleteExtraEvent(Long id, Model model) throws IOException {
		boService.deleteExtraEvent(id);
		return "redirect:/admin/extraevent/dashboard";
	}

}
