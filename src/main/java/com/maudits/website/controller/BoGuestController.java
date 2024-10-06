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
import com.maudits.website.domain.form.GuestForm;
import com.maudits.website.service.BoGuestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}/guest")
public class BoGuestController {
	private final BoGuestService boGuestService;

	@GetMapping("create/new")
	public String createGuest(@PathVariable Display edition, Model model) {
		model.addAttribute("form", boGuestService.createGuestForm());
		return "bo/guest-create-or-edit";
	}

	@GetMapping("edit/{id}")
	public String editGuest(@PathVariable Long id, Model model) {
		model.addAttribute("form", boGuestService.findGuestFormFromId(id));
		return "bo/guest-create-or-edit";
	}

	@PostMapping("save-edit")
	public String saveGuest(@PathVariable Display edition, @Validated GuestForm form, Model model)
			throws IOException {
		boGuestService.saveGuest(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#guestAnchor";
	}

	@PostMapping("delete")
	public String deleteGuest(@PathVariable Display edition, Long id, Model model) throws IOException {
		boGuestService.deleteGuest(id);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#guestAnchor";
	}
}
