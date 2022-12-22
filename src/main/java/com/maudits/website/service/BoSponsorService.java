package com.maudits.website.service;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.form.SponsorForm;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.SponsorRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoSponsorService {
	private final SponsorRepository sponsorRepository;
	private final EditionRepository editionRepository;
	private final UploadService uploadService;

	public Edition findEdition(DisplayEdition displayEdition) {
		switch (displayEdition) {
		case CURRENT:
			return editionRepository.findOneByCurrentTrue();
		case NEXT:
			return editionRepository.findOneByNextTrue();
		default:
			throw new IllegalArgumentException("Unexpected value: " + displayEdition);
		}
	}

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

	public void saveSponsor(@PathVariable DisplayEdition edition, @Valid SponsorForm form) throws IOException {
		Long id = form.getId();
		Sponsor sponsor = (id != null) ? sponsorRepository.findById(id).orElseThrow() : new Sponsor();
		sponsor.setName(filterEmpty(form.getName()));
		sponsor.setTargetUrl(filterEmpty(form.getTargetUrl()));

		sponsor.setEdition(findEdition(edition));

		var logoFile = form.getLogoFile();
		if (!logoFile.isEmpty()) {
			var tmp = logoFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(sponsor.getEdition().getName(),
					"sponsor_" + sponsor.getName() + fileExtension, logoFile);
			sponsor.setLogoUrl(url);
		}
		sponsorRepository.save(sponsor);
	}

	public void deleteSponsor(Long id) {
		sponsorRepository.deleteById(id);
	}
}
