package com.maudits.website.domain.displayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class HomePageFilmDisplayer {
	private final Long id;
	private final String title;
	private final String textualId;
	private final String description;
	private final String startTime;
	private final String imageUrl;
	private final String locationName;

	public HomePageFilmDisplayer(Film film) {
		if (film.getRevealTime() != null && film.getRevealTime().isBefore(LocalDateTime.now())) {
			this.id = film.getId();
			this.textualId = film.getTextualId();
			this.title = film.getTitle();
			this.description = film.getDescription();
			if (film.getStartTime() == null) {
				this.startTime = "Pas encore annoncé";
			} else if (film.getStartTime().getHour() == 0) {
				this.startTime = "Minuit";
			} else if (film.getStartTime().getMinute() == 0) {
				this.startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'"));
			} else {
				this.startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'mm"));
			}
			this.imageUrl = film.getPosterUrl();
			this.locationName = film.getLocationName();
		} else {
			this.id = null;
			this.textualId = null;
			this.title = null;
			this.description = null;
			if (film.getStartTime() == null) {
				this.startTime = "Pas encore annoncé";
			} else if (film.getStartTime().getHour() == 0) {
				this.startTime = "Minuit";
			} else if (film.getStartTime().getMinute() == 0) {
				this.startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'"));
			} else {
				this.startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'mm"));
			}
			this.imageUrl = null;
			this.locationName = film.getLocationName();
		}
	}
}
