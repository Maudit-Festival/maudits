package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.domain.form.PressLogoForm;
import com.maudits.website.service.BoPressService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/press")
public class BoPressController {
	private final BoPressService boEditionService;

	@GetMapping("dashboard")
	public String editionDisplay(Model model) {
		model.addAttribute("pressLogos", boEditionService.findPressLogos());
		return "bo/press-dashboard";
	}

	@PostMapping("upload-document")
	public String uploadDocument(MultipartFile file) throws IOException {
		boEditionService.uploadDocument(file);
		return "redirect:/bo/press/dashboard";
	}

	@GetMapping("logo/create/new")
	public String createPressLogo(Model model) {
		model.addAttribute("form", boEditionService.createPressLogoForm());
		return "bo/press-logo-create-or-edit";
	}

	@GetMapping("logo/edit/{id}")
	public String editPressLogo(@PathVariable Long id, Model model) {
		model.addAttribute("form", boEditionService.findPressLogoFormFromId(id));
		return "bo/press-logo-create-or-edit";
	}

	@PostMapping("logo/save-edit")
	public String savePressLogo(@Validated PressLogoForm form, Model model) throws IOException {
		boEditionService.savePressLogo(form);
		return "redirect:/bo/press/dashboard";
	}

	@PostMapping("logo/delete")
	public String deletePressLogo(Long id, Model model) throws IOException {
		boEditionService.deletePressLogo(id);
		return "redirect:/bo/press/dashboard";
	}
}
