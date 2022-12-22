package com.maudits.website.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.form.SponsorForm;
import com.maudits.website.service.BoSponsorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}/sponsor")
public class BoSponsorsController {
	private final BoSponsorService boSponsorService;

	@GetMapping("/create/new")
	public String showFilmCreationNextEdition(@PathVariable DisplayEdition edition, Model model) {
		model.addAttribute("form", boSponsorService.createSponsorForm());
		return "bo/sponsor-create-or-edit";
	}

	@GetMapping("/edit/{id}")
	public String showFilmEdition(@PathVariable DisplayEdition edition, @PathVariable Long id, Model model) {
		model.addAttribute("form", boSponsorService.findSponsorFormFromId(id));
		return "bo/sponsor-create-or-edit";
	}

	@PostMapping("/save-edit")
	public String saveFilmEdition(@PathVariable DisplayEdition edition, @Valid SponsorForm form, Model model)
			throws IOException {
		boSponsorService.saveSponsor(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("delete")
	public String deleteSponsor(@PathVariable DisplayEdition edition, Long id, Model model) throws IOException {
		boSponsorService.deleteSponsor(id);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}

}
