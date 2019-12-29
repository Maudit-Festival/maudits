package com.maudits.website.domain.displayer;

import java.time.LocalTime;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class HomePageFilmDisplayer {
	private final Long id;
	private final String title;
	private final String description;
	private final LocalTime startTime;
	private final String imageUrl;

	public HomePageFilmDisplayer(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.startTime = film.getStartTime();
		this.imageUrl = film.getThumbnailPosterUrl();
	}
}
