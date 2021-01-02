package com.maudits.website.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.bo.displayer.SponsorBoDisplayer;
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

	public List<SponsorBoDisplayer> findCurrentSponsors() {
		return findSponsors(editionRepository.findOneByCurrentTrue());
	}

	public List<SponsorBoDisplayer> findNextSponsors() {
		return findSponsors(editionRepository.findOneByNextTrue());
	}

	private List<SponsorBoDisplayer> findSponsors(Edition edition) {
		List<SponsorBoDisplayer> result = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			result.add(new SponsorBoDisplayer(sponsor));
		}
		return result;
	}

	public SponsorForm createSponsorFormNextEdition() {
		return new SponsorForm(true);
	}

	public SponsorForm createSponsorFormCurrentEdition() {
		return new SponsorForm(false);
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

	public void saveSponsor(@Valid SponsorForm form) throws IOException {
		Long id = form.getId();
		Sponsor sponsor = (id != null) ? sponsorRepository.findById(id).orElseThrow() : new Sponsor();
		sponsor.setName(filterEmpty(form.getName()));
		sponsor.setTargetUrl(filterEmpty(form.getTargetUrl()));

		sponsor.setEdition(form.isNextEdition() ? editionRepository.findOneByNextTrue()
				: editionRepository.findOneByCurrentTrue());

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
}
