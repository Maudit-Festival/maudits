package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
	public String createSponsor(@PathVariable DisplayEdition edition, Model model) {
		model.addAttribute("form", boSponsorService.createSponsorForm());
		return "bo/sponsor-create-or-edit";
	}

	@GetMapping("/edit/{id}")
	public String editSponsor(@PathVariable DisplayEdition edition, @PathVariable Long id, Model model) {
		model.addAttribute("form", boSponsorService.findSponsorFormFromId(id));
		return "bo/sponsor-create-or-edit";
	}

	@PostMapping("/save-edit")
	public String saveSponsor(@PathVariable DisplayEdition edition, @Validated SponsorForm form, Model model)
			throws IOException {
		boSponsorService.saveSponsor(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#sponsorAnchor";
	}

	@PostMapping("delete")
	public String deleteSponsor(@PathVariable DisplayEdition edition, Long id, Model model) throws IOException {
		boSponsorService.deleteSponsor(id);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#sponsorAnchor";
	}

	@GetMapping("/copy")
	public String showSponsorsAvailableForCopy(@PathVariable DisplayEdition edition, Model model) {
		model.addAttribute("sponsors", boSponsorService.findSponsorsAvailableForCopy(edition));
		return "bo/sponsor-copy-list";
	}

	@GetMapping("/copy/{id}")
	public String showCopySponsorForm(@PathVariable DisplayEdition edition, @PathVariable Long id, Model model) {
		model.addAttribute("form", boSponsorService.findSponsorFormFromId(id));
		model.addAttribute("copy", true);
		return "bo/sponsor-create-or-edit";
	}

}
