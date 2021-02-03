package com.maudits.website.domain.displayer;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class EditionFilm {
	private final String title;

	public EditionFilm(Film film) {
		this.title = film.getTitle();
	}
}
