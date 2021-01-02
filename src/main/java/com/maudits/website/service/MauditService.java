package com.maudits.website.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.displayer.FilmDetailDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.HomePageFilmDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.domain.page.HomepageDisplayer;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.FilmRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MauditService {
	private final FilmRepository filmRepository;
	private final EditionRepository editionRepository;

	public FilmDetailDisplayer findFilmDisplayerFromTextualId(String textualId) {
		return new FilmDetailDisplayer(filmRepository.findOneByTextualId(textualId)
				.or(() -> filmRepository.findById(Long.valueOf(textualId))).orElseThrow());
	}

	private List<Film> mergeList(List<Film> oldList, List<Film> newList) {
		if (oldList == null) {
			return newList;
		} else {
			List<Film> result = new ArrayList<>();
			result.addAll(oldList);
			result.addAll(newList);
			return result;
		}
	}

	public HomepageDisplayer makeHomeFilmRecapCurrentEdition() {
		return makeHomeFilmRecap(editionRepository.findOneByCurrentTrue());
	}

	public HomepageDisplayer makeHomeFilmRecapNextEdition() {
		return makeHomeFilmRecap(editionRepository.findOneByNextTrue());
	}

	private HomepageDisplayer makeHomeFilmRecap(Edition edition) {
		List<HomePageDayDisplayer> days = new ArrayList<>();
		Map<LocalDate, List<Film>> films = new HashMap<>();
		for (Film film : edition.getFilms()) {
			films.merge(film.getDate(), List.of(film), this::mergeList);
		}
		for (Entry<LocalDate, List<Film>> entry : films.entrySet()) {
			LocalDate date = entry.getKey();
			List<HomePageFilmDisplayer> filmsForDate = entry.getValue().stream().map(HomePageFilmDisplayer::new)
					.sorted(Comparator.comparing(HomePageFilmDisplayer::getStartTime)).collect(Collectors.toList());
			days.add(new HomePageDayDisplayer(date, filmsForDate));
		}
		days.sort(Comparator.comparing(HomePageDayDisplayer::getDate));

		List<SponsorDisplayer> sponsors = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			sponsors.add(new SponsorDisplayer(sponsor));
		}
		Collections.shuffle(sponsors);
		return new HomepageDisplayer(days, sponsors);
	}

}
