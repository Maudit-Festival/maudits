package com.maudits.website.domain.displayer;

import java.time.format.DateTimeFormatter;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class HomePageFilmDisplayer {
	private final Long id;
	private final String title;
	private final String description;
	private final String startTime;
	private final String imageUrl;
	private final String locationName;

	public HomePageFilmDisplayer(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.startTime = (film.getStartTime().getHour() != 0)
				? film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'"))
				: "Minuit";
		this.imageUrl = film.getThumbnailPosterUrl();
		this.locationName = film.getLocationName();
	}
}
