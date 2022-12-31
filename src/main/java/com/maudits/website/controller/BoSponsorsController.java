package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.form.SponsorForm;
import com.maudits.website.service.BoSponsorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/sponsors")
public class BoSponsorsController {
	private final BoSponsorService boSponsorService;

	@GetMapping("")
	public String boHomepage(Model model) {
		model.addAttribute("currentSponsors", boSponsorService.findCurrentSponsors());
		model.addAttribute("nextSponsors", boSponsorService.findNextSponsors());
		return "bo/sponsors";
	}

	@GetMapping("/edit/{id}")
	public String showFilmEdition(@PathVariable Long id, Model model) {
		model.addAttribute("form", boSponsorService.findSponsorFormFromId(id));
		return "bo/sponsor-create-or-edit";
	}

	@GetMapping("/create-next-edition")
	public String showFilmCreationNextEdition(Model model) {
		model.addAttribute("form", boSponsorService.createSponsorFormNextEdition());
		return "bo/sponsor-create-or-edit";
	}

	@GetMapping("/create-current-edition")
	public String showFilmCreationCurrentEdition(Model model) {
		model.addAttribute("form", boSponsorService.createSponsorFormCurrentEdition());
		return "bo/sponsor-create-or-edit";
	}

	@PostMapping("/save-edit")
	public String saveFilmEdition(SponsorForm form, Model model) throws IOException {
		boSponsorService.saveSponsor(form);
		return "redirect:/bo/sponsors";
	}

}
