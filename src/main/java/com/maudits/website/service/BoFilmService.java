package com.maudits.website.service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.bo.displayer.ExtraEventBoDisplayer;
import com.maudits.website.domain.bo.displayer.FilmBoDisplayer;
import com.maudits.website.domain.form.ExtraEventForm;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.ExtraEventRepository;
import com.maudits.website.repository.FilmRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.ExtraEvent;
import com.maudits.website.repository.entities.Film;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoFilmService {
	private final FilmRepository filmRepository;
	private final EditionRepository editionRepository;
	private final UploadService uploadService;
	private final ExtraEventRepository extraEventRepository;

	public List<FilmBoDisplayer> findCurrentFilms() {
		return findFilms(editionRepository.findOneByCurrentTrue());
	}

	public List<FilmBoDisplayer> findNextFilms() {
		return findFilms(editionRepository.findOneByNextTrue());
	}

	private List<FilmBoDisplayer> findFilms(Edition edition) {
		List<FilmBoDisplayer> result = new ArrayList<>();
		for (Film film : edition.getFilms()) {
			result.add(new FilmBoDisplayer(film));
		}
		return result;
	}

	public FilmForm createFilmFormNextEdition() {
		return new FilmForm(true);
	}

	public FilmForm createFilmFormCurrentEdition() {
		return new FilmForm(false);
	}

	public FilmForm findFilmFormFromId(Long id) {
		return new FilmForm(filmRepository.findById(id).orElseThrow());
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

	public Film saveFilm(@Valid FilmForm form) throws IOException {
		Long id = form.getId();
		Film film = (id != null) ? filmRepository.findById(id).orElseThrow() : new Film();
		film.setEdition(form.isNextEdition() ? editionRepository.findOneByNextTrue()
				: editionRepository.findOneByCurrentTrue());

		return saveFilm(form, film);
	}

	public Film saveFilm(@Valid FilmForm form, Film film) throws IOException {
		film.setTextualId(form.getTextualId());
		film.setTitle(form.getTitle());
		film.setDescription(form.getDescription());
		film.setDate(form.getDate());
		film.setStartTime(form.getStartTime());
		film.setRevealTime(form.getRevealTime());

		var posterFile = form.getPosterFile();
		String folder = film.getEdition() != null ? film.getEdition().getName()
				: "extraevent/" + film.getDate().getYear();
		if (!posterFile.isEmpty()) {
			var tmp = posterFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "poster_" + film.getTextualId() + fileExtension, posterFile);
			film.setPosterUrl(url);
			film.setThumbnailPosterUrl(url);
		}
		var sampleImageFile = form.getSampleImageFile();
		if (!sampleImageFile.isEmpty()) {
			var tmp = sampleImageFile.getOriginalFilename().split(".");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "sample_image" + film.getTextualId() + fileExtension,
					sampleImageFile);
			film.setSampleImageUrl(url);
		}

		film.setReleaseDate(filterEmpty(form.getReleaseDate()));
		film.setDirector(filterEmpty(form.getDirector()));
		film.setScenarist(filterEmpty(form.getScenarist()));
		film.setNationality(filterEmpty(form.getNationality()));
		film.setDuration(filterEmpty(form.getDuration()));
		film.setCasting(filterEmpty(form.getCasting()));
		film.setPartner(filterEmpty(form.getPartner()));
		film.setBuyUrl(filterEmpty(form.getBuyUrl()));
		film.setAgeRating(filterEmpty(form.getAgeRating()));
		film.setLocationName(filterEmpty(form.getLocationName()));
		film.setLocationAddress(filterEmpty(form.getLocationAddress()));

		film.setLastUpdateTime(ZonedDateTime.now());

		return filmRepository.save(film);
	}

	public void deleteFilm(Long id) {
		filmRepository.deleteById(id);
	}

	public List<ExtraEventBoDisplayer> findCurrentExtraEvents() {
		return extraEventRepository.findFutureEvents().stream().map(ExtraEventBoDisplayer::new).toList();
	}

	public List<ExtraEventBoDisplayer> findPastExtraEvents() {
		return extraEventRepository.findPastEvents().stream().map(ExtraEventBoDisplayer::new).toList();
	}

	public ExtraEventForm createExtraEvent() {
		return new ExtraEventForm();
	}

	public ExtraEventForm findExtraEventFormFromId(Long id) {
		return new ExtraEventForm(extraEventRepository.findById(id).orElseThrow());
	}

	public void saveExtraEvent(@Valid ExtraEventForm form) throws IOException {
		Long eventId = form.getEventId();
		ExtraEvent event = (eventId != null) ? extraEventRepository.findById(eventId).orElseThrow() : new ExtraEvent();
		Film film = saveFilm(form, new Film());
		event.setFilm(film);
		event.setRevealTime(form.getRevealTime());
		event.setHideTime(form.getDate().plusDays(2).atStartOfDay());
		extraEventRepository.save(event);
	}

	public void deleteExtraEvent(Long id) {
		extraEventRepository.deleteById(id);
	}

}
