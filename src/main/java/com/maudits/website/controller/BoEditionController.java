package com.maudits.website.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.form.EditionForm;
import com.maudits.website.service.BoEditionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}")
public class BoEditionController {
	private final BoEditionService boEditionService;

	@GetMapping("dashboard")
	public String editionDisplay(@PathVariable Display edition, Model model) {
		model.addAttribute("edition", edition);
		model.addAttribute("page", boEditionService.buildDisplayer(edition));
		model.addAttribute("form", boEditionService.buildForm(edition));
		return "bo/edition-dashboard";
	}

	@PostMapping("save")
	public String editionSave(@PathVariable Display edition, @Validated EditionForm form) throws IOException {
		boEditionService.saveEdition(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("current")
	public String makeEditionCurrent(@PathVariable Display edition) throws IOException {
		if (edition != Display.NEXT) {
			throw new RuntimeException();
		}
		boEditionService.makeEditionCurrent();
		return "redirect:/bo/" + Display.CURRENT.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("pictures")
	public String editionPicturesSave(@PathVariable Display edition, String password, List<MultipartFile> files)
			throws IOException {
		if (edition != Display.CURRENT) {
			throw new RuntimeException();
		}
		boEditionService.editionPicturesSave(password, files);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard";
	}
}
