package com.maudits.website.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.bo.displayer.EditionBoDisplayer;
import com.maudits.website.domain.bo.displayer.FilmBoDisplayer;
import com.maudits.website.domain.bo.displayer.SponsorBoDisplayer;
import com.maudits.website.domain.form.EditionForm;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoEditionService {
	private final EditionRepository editionRepository;

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

	private List<FilmBoDisplayer> findFilms(Edition edition) {
		List<FilmBoDisplayer> result = new ArrayList<>();
		for (Film film : edition.getFilms()) {
			result.add(new FilmBoDisplayer(film));
		}
		return result;
	}

	private List<SponsorBoDisplayer> findSponsors(Edition edition) {
		List<SponsorBoDisplayer> result = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			result.add(new SponsorBoDisplayer(sponsor));
		}
		return result;
	}

	public EditionBoDisplayer buildDisplayer(DisplayEdition displayEdition) {
		Edition edition = findEdition(displayEdition);
		return new EditionBoDisplayer(findFilms(edition), findSponsors(edition));
	}

	public EditionForm buildForm(DisplayEdition displayEdition) {
		Edition edition = findEdition(displayEdition);
		return new EditionForm(edition);
	}

	public void saveEdition(DisplayEdition displayEdition, @Valid EditionForm form) {
		Edition edition = findEdition(displayEdition);
		edition.setAccentColor(form.getColor());
		editionRepository.save(edition);
	}
}
