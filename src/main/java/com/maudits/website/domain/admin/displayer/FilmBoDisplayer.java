package com.maudits.website.domain.admin.displayer;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmBoDisplayer {
	private final Long id;
	private final String title;
	private final String posterUrl;

	public FilmBoDisplayer(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.posterUrl = film.getPosterUrl();
	}
}
