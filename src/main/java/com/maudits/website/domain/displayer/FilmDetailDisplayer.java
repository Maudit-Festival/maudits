package com.maudits.website.domain.displayer;

import java.time.LocalDate;
import java.time.LocalTime;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailDisplayer {
	private final String title;
	private final String description;
	private final LocalDate date;
	private final LocalTime startTime;
	private final String posterUrl;
	private final String releaseDate;
	private final String casting;
	private final String format;

	public FilmDetailDisplayer(Film film) {
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.date = film.getDate();
		this.startTime = film.getStartTime();
		this.posterUrl = film.getPosterUrl();
		this.releaseDate = film.getReleaseDate();
		this.casting = film.getCasting();
		this.format = film.getFormat();
	}
}
