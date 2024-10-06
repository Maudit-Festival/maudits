package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.form.CrewForm;
import com.maudits.website.service.BoCrewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("bo/{edition}/crew")
public class BoCrewController {
	private final BoCrewService boCrewService;

	@PostMapping("add")
	public String addCrew(@PathVariable Display edition, @Validated CrewForm form) throws IOException {
		boCrewService.saveCrew(edition, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#teamAnchor";
	}

	@PostMapping("edit/{crewId}")
	public String editCrew(@PathVariable Display edition, @PathVariable Long crewId, @Validated CrewForm form)
			throws IOException {
		boCrewService.editCrew(edition, crewId, form);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#teamAnchor";
	}

	@PostMapping("delete/{crewId}")
	public String addCrew(@PathVariable Display edition, @PathVariable Long crewId) throws IOException {
		boCrewService.deleteCrew(edition, crewId);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#teamAnchor";
	}

	@PostMapping("position/add")
	public String addPosition(@PathVariable Display edition, @RequestParam String positionName,
			@RequestParam(required = false) Long previousPositionId) throws IOException {
		boCrewService.addPosition(positionName, previousPositionId);
		return "redirect:/bo/" + edition.name().toLowerCase() + "/dashboard#teamAnchor";
	}
}
