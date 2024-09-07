package com.maudits.website.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.HomePageFilmDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.domain.exception.WrongEditionException;
import com.maudits.website.domain.page.AboutPageDisplayer;
import com.maudits.website.domain.page.ArchivePageDisplayer;
import com.maudits.website.domain.page.FilmDetailPageDisplayer;
import com.maudits.website.domain.page.FrontPageDisplayer;
import com.maudits.website.domain.page.HomepageDisplayer;
import com.maudits.website.domain.page.PreviousEditionDisplayer;
import com.maudits.website.repository.CrewRepository;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.ExtraEventRepository;
import com.maudits.website.repository.FilmRepository;
import com.maudits.website.repository.entities.BoothPicture;
import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;
import com.maudits.website.repository.entities.Position;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MauditService {
	private final CurrentEditionService currentEditionService;
	private final EditionRepository editionRepository;
	private final FilmRepository filmRepository;
	private final ExtraEventRepository extraEventRepository;
	private final CrewRepository crewRepository;

	private List<String> findPreviousEditionNames(DisplayEdition displayEdition) {
		List<Edition> pastEditions = editionRepository.findAllByCurrentFalseAndNextFalse();
		if (displayEdition == DisplayEdition.NEXT) {
			pastEditions.add(0, editionRepository.findOneByCurrentTrue());
		}
		return pastEditions.stream().map(Edition::getName).sorted().toList();
	}

	public FrontPageDisplayer makePageDisplayer(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		return new FrontPageDisplayer(edition, findPreviousEditionNames(displayEdition));
	}

	private Optional<Film> findById(String id) {
		try {
			return filmRepository.findById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public FilmDetailPageDisplayer findFilmDetailPageDisplayerFromTextualId(DisplayEdition displayEdition,
			String textualId) throws WrongEditionException {
		Edition edition = currentEditionService.findEdition(displayEdition);
		Film film = filmRepository.findOneByTextualId(textualId).or(() -> findById(textualId)).orElseThrow();
		if (displayEdition != DisplayEdition.NEXT && film.isNextEdition()) {
			throw new WrongEditionException();
		}
		return new FilmDetailPageDisplayer(edition, findPreviousEditionNames(displayEdition), film);
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

	private List<HomePageDayDisplayer> findDays(Edition edition) {
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
		return days;
	}

	public HomepageDisplayer makeHomeFilmRecap(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		List<HomePageDayDisplayer> days = findDays(edition);
		List<SponsorDisplayer> sponsors = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			sponsors.add(new SponsorDisplayer(sponsor));
		}
		Collections.shuffle(sponsors);
//		return new HomepageDisplayer(edition, days, sponsors);
		return extraEventRepository.findOneByActive()
				.map(ea -> new HomepageDisplayer(edition, findPreviousEditionNames(displayEdition),
						new HomePageCurrentEventDisplayer(ea.getFilm()), sponsors))
				.orElse(new HomepageDisplayer(edition, findPreviousEditionNames(displayEdition), days, sponsors));
	}

	public ArchivePageDisplayer makeArchivePage(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		List<Edition> pastEditions = editionRepository.findAllByCurrentFalseAndNextFalse();
		if (displayEdition == DisplayEdition.NEXT) {
			pastEditions.add(0, editionRepository.findOneByCurrentTrue());
		}
		return new ArchivePageDisplayer(edition, pastEditions);
	}

	public PreviousEditionDisplayer makePreviousEditionPage(String editionName, DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		Edition archivedEdition = editionRepository.findByName(editionName).orElseThrow();
		List<HomePageDayDisplayer> days = findDays(archivedEdition);
		return new PreviousEditionDisplayer(edition, findPreviousEditionNames(displayEdition), archivedEdition, days);
	}

	public FrontPageDisplayer makeAboutPageDisplayer(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		Map<Position, List<Crew>> credits = new HashMap<>();
		List<Crew> crews = crewRepository.findAllByEdition(edition);
		for (Crew crew : crews) {
			if (credits.containsKey(crew.getPosition())) {
				credits.get(crew.getPosition()).add(crew);
			} else {
				credits.put(crew.getPosition(), new ArrayList<>(List.of(crew)));
			}
		}
		return new AboutPageDisplayer(edition, findPreviousEditionNames(displayEdition), credits);
	}

	public List<String> findBoothPictures(String password) {
		Edition edition = currentEditionService.findEdition(DisplayEdition.CURRENT);
		if (!edition.getBoothPicturesPassword().equals(password)) {
			throw new RuntimeException("Mot de passe incorrect");
		}
		return edition.getBoothPictures().stream().map(BoothPicture::getImageUrl).toList();
	}
}
