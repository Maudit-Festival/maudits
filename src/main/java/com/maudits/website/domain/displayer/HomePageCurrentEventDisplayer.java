package com.maudits.website.domain.displayer;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class HomePageCurrentEventDisplayer {
	private final Long id;
	private final String title;
	private final String textualId;
	private final String description;
	private final String dateAndTime;
	private final String imageUrl;
	private final String locationName;

	public HomePageCurrentEventDisplayer(Film film) {
		this.id = film.getId();
		this.textualId = film.getTextualId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		String startTime;
		if (film.getStartTime() == null) {
			startTime = "Pas encore annoncé";
		} else if (film.getStartTime().getHour() == 0) {
			startTime = "Minuit";
		} else if (film.getStartTime().getMinute() == 0) {
			startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'"));
		} else {
			startTime = film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'mm"));
		}
		String date = film.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRANCE));
		this.dateAndTime = date + " à " + startTime;
		this.imageUrl = film.getThumbnailPosterUrl();
		this.locationName = film.getLocationName();
	}
}
