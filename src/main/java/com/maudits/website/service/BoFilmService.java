package com.maudits.website.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.admin.displayer.ExtraEventBoDisplayer;
import com.maudits.website.domain.form.ExtraEventForm;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.repository.ExtraEventRepository;
import com.maudits.website.repository.FilmRepository;
import com.maudits.website.repository.entities.ExtraEvent;
import com.maudits.website.repository.entities.Film;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoFilmService {
	private final CurrentEditionService currentEditionService;
	private final FilmRepository filmRepository;
	private final UploadService uploadService;
	private final ExtraEventRepository extraEventRepository;

	public FilmForm createFilmForm() {
		return new FilmForm();
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

	public Film saveFilm(Display displayEdition, @Validated FilmForm form) throws IOException {
		Long id = form.getId();
		Film film = (id != null) ? filmRepository.findById(id).orElseThrow() : new Film();
		film.setEdition(currentEditionService.findEdition(displayEdition));
		return saveFilm(form, film);
	}

	public Film saveFilm(@Validated FilmForm form, Film film) throws IOException {
		film.setTextualId(filterEmpty(form.getTextualId()));
		film.setTitle(filterEmpty(form.getTitle()));
		film.setDescription(filterEmpty(form.getDescription()));
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
		film.setSponsor(form.getSponsor());
		film.setTriggerWarnings(form.getTriggerWarnings());

		film.setLastUpdateTime(ZonedDateTime.now());

		return filmRepository.save(film);
	}

	public void deleteFilm(Long id) {
		// TODO remove image
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

	public void saveExtraEvent(@Validated ExtraEventForm form) throws IOException {
		Long eventId = form.getEventId();
		ExtraEvent event = (eventId != null) ? extraEventRepository.findById(eventId).orElseThrow() : new ExtraEvent();
		Film film = saveFilm(form, event.getFilm() != null ? event.getFilm() : new Film());
		event.setFilm(film);
		event.setRevealTime(form.getRevealTime());
		event.setHideTime(form.getDate().plusDays(2).atStartOfDay());
		extraEventRepository.save(event);
	}

	public void deleteExtraEvent(Long id) {
		// TODO Delete image and movie
		extraEventRepository.deleteById(id);
	}

	public void saveAndPublishFilm(Display edition, FilmForm form) throws IOException {
		if (form.getRevealTime() == null)
			form.setRevealTime(LocalDateTime.now());
		saveFilm(edition, form);
	}

	public void saveWithoutPublishingFilm(Display edition, FilmForm form) throws IOException {
		form.setRevealTime(null);
		saveFilm(edition, form);
	}
}
