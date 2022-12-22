package com.maudits.website.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.form.EditionForm;
import com.maudits.website.service.BoEditionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}")
public class BoEditionController {
	private final BoEditionService boEditionService;

	@GetMapping("dashboard")
	public String editionDisplay(@PathVariable DisplayEdition edition, Model model) {
		model.addAttribute("page", boEditionService.buildDisplayer(edition));
		model.addAttribute("form", boEditionService.buildForm(edition));
		return "bo/edition-dashboard";
	}

	@PostMapping("save")
	public String editionSave(@PathVariable DisplayEdition edition, @Valid EditionForm form) {
		System.out.println(form.getColor());
		boEditionService.saveEdition(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}
}
