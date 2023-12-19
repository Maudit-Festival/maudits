package com.maudits.website.service;

import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> refs/heads/spring_boot_upgrade

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.form.SponsorForm;
import com.maudits.website.repository.SponsorRepository;
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

	public void saveSponsor(@PathVariable DisplayEdition edition, @Validated SponsorForm form) throws IOException {
		Long id = form.getId();
		Sponsor sponsor = (id != null) ? sponsorRepository.findById(id).orElseThrow() : new Sponsor();
		sponsor.setName(filterEmpty(form.getName()));
		sponsor.setTargetUrl(filterEmpty(form.getTargetUrl()));

		sponsor.setEdition(currentEditionService.findEdition(edition));

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
