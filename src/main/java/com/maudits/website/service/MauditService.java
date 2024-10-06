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

import com.maudits.website.domain.Display;
import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.HomePageFilmDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.domain.exception.WrongEditionException;
import com.maudits.website.domain.page.AboutPage;
import com.maudits.website.domain.page.FilmDetailPage;
import com.maudits.website.domain.page.FrontPage;
import com.maudits.website.domain.page.HomePage;
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

	private List<String> findPreviousEditionNames(Display display) {
		List<Edition> pastEditions = editionRepository.findAllByCurrentFalseAndNextFalse();
		if (display == Display.NEXT) {
			pastEditions.add(0, editionRepository.findOneByCurrentTrue());
		}
		return pastEditions.stream().map(Edition::getName).sorted().toList();
	}

	private FrontPageDisplayer makeFrontPageDisplayer(Display display, Edition edition) {
		return new FrontPageDisplayer(display, edition, findPreviousEditionNames(display));
	}

	private FrontPageDisplayer makeFrontPageDisplayer(Display display) {
		return makeFrontPageDisplayer(display, currentEditionService.findEdition(display));
	}

	public FrontPage makeFrontPage(Display display) {
		return new FrontPage(makeFrontPageDisplayer(display));
	}

	private Optional<Film> findById(String id) {
		try {
			return filmRepository.findById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public FilmDetailPage findFilmDetailPageDisplayerFromTextualId(Display display, String textualId)
			throws WrongEditionException {
		Film film = filmRepository.findOneByTextualId(textualId).or(() -> findById(textualId)).orElseThrow();
		if (display != Display.NEXT && film.isNextEdition()) {
			throw new WrongEditionException();
		}
		FrontPageDisplayer frontPageDisplayer = makeFrontPageDisplayer(display, film.getEdition());
		return new FilmDetailPage(frontPageDisplayer, film);
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

	public HomePage makeHomeFilmRecap(Display display) {
		FrontPageDisplayer frontPageDisplayer = makeFrontPageDisplayer(display);
		return makeHomeFilmRecap(frontPageDisplayer);
	}

	public HomePage makeHomeFilmRecap(Display display, String editionName) {
		Edition archivedEdition = editionRepository.findByName(editionName).orElseThrow();
		FrontPageDisplayer frontPageDisplayer = makeFrontPageDisplayer(display, archivedEdition);
		return makeHomeFilmRecap(frontPageDisplayer);
	}

	private HomePage makeHomeFilmRecap(FrontPageDisplayer frontPageDisplayer) {
		Edition edition = frontPageDisplayer.getEdition();
		List<HomePageDayDisplayer> days = findDays(edition);
		List<SponsorDisplayer> sponsors = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			sponsors.add(new SponsorDisplayer(sponsor));
		}
		Collections.shuffle(sponsors);
		return extraEventRepository.findOneByActive()
				.map(ea -> new HomePage(frontPageDisplayer, new HomePageCurrentEventDisplayer(ea.getFilm()), sponsors))
				.orElse(new HomePage(frontPageDisplayer, days, sponsors));
	}

//	public PreviousEditionPage makePreviousEditionPage(String editionName, Display display) {
//		Edition edition = currentEditionService.findEdition(display);
//		Edition archivedEdition = editionRepository.findByName(editionName).orElseThrow();
//		List<HomePageDayDisplayer> days = findDays(archivedEdition);
//		return new PreviousEditionPage(edition, findPreviousEditionNames(display), display, archivedEdition, days);
//	}

	public FrontPage makeAboutPageDisplayer(Display display) {
		FrontPageDisplayer displayer = makeFrontPageDisplayer(display);
		return makeAboutPageDisplayer(displayer);
	}

	public FrontPage makeAboutPageDisplayer(Display display, String editionName) {
		Edition edition = editionRepository.findByName(editionName).orElseThrow();
		FrontPageDisplayer displayer = makeFrontPageDisplayer(display, edition);
		return makeAboutPageDisplayer(displayer);
	}

	private FrontPage makeAboutPageDisplayer(FrontPageDisplayer displayer) {
		Map<Position, List<Crew>> credits = new HashMap<>();
		List<Crew> crews = crewRepository.findAllByEdition(displayer.getEdition());
		for (Crew crew : crews) {
			if (credits.containsKey(crew.getPosition())) {
				credits.get(crew.getPosition()).add(crew);
			} else {
				credits.put(crew.getPosition(), new ArrayList<>(List.of(crew)));
			}
		}
		return new AboutPage(displayer, credits);
	}

	public List<String> findBoothPictures(String password) {
		Edition edition = currentEditionService.findEdition(Display.CURRENT);
		if (!edition.getBoothPicturesPassword().equals(password)) {
			throw new RuntimeException("Mot de passe incorrect");
		}
		return edition.getBoothPictures().stream().map(BoothPicture::getImageUrl).toList();
	}
}
