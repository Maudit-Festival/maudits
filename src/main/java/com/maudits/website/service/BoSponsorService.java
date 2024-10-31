package com.maudits.website.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.admin.displayer.SponsorBoDisplayer;
import com.maudits.website.domain.form.SponsorForm;
import com.maudits.website.repository.SponsorRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoSponsorService {
	private final CurrentEditionService currentEditionService;
	private final UploadService uploadService;
	private final SponsorRepository sponsorRepository;

	public SponsorForm createSponsorForm() {
		return new SponsorForm();
	}

	public SponsorForm findSponsorFormFromId(Long id) {
		return new SponsorForm(sponsorRepository.findById(id).orElseThrow());
	}

	private String filterEmpty(String string) {
		if (string == null) {
			return null;
		} else if (string.isBlank()) {
			return null;
		} else {
			return string;
		}
	}

	public void saveSponsor(Display edition, @Validated SponsorForm form) throws IOException {
		Long id = form.getId();
		Sponsor sponsor = (id != null) ? sponsorRepository.findById(id).orElseThrow() : new Sponsor();
		sponsor.setName(filterEmpty(form.getName()));
		sponsor.setTextualId(form.getTextualId());
		sponsor.setTargetUrl(filterEmpty(form.getTargetUrl()));

		sponsor.setDate(LocalDate.now());

		sponsor.setEdition(currentEditionService.findEdition(edition));

		if (form.getLogoUrl() != null && !form.getLogoUrl().isBlank()) {
			sponsor.setLogoUrl(form.getLogoUrl());
		}

		var logoFile = form.getLogoFile();
		if (!logoFile.isEmpty()) {
			var tmp = logoFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(sponsor.getEdition().getName(),
					"sponsor_" + sponsor.getTextualId() + fileExtension, logoFile);
			sponsor.setLogoUrl(url);
		}
		sponsorRepository.save(sponsor);
	}

	public void deleteSponsor(Long id) {
		sponsorRepository.deleteById(id);
	}

	public List<SponsorBoDisplayer> findSponsorsAvailableForCopy(Display displayEdition) {
		Map<String, Sponsor> map = new HashMap<>();
		for (Sponsor sponsor : sponsorRepository.findAll()) {
			map.merge(sponsor.getTextualId(), sponsor, (s1, s2) -> s1.getDate().isAfter(s2.getDate()) ? s1 : s2);
		}
		Edition edition = currentEditionService.findEdition(displayEdition);
		return map.values().stream().filter(s -> !s.getEdition().equals(edition)).map(SponsorBoDisplayer::new).toList();
	}
}
