package com.maudits.website.domain.bo.displayer;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmBoDisplayer {
	private final Long id;
	private final String title;

	public FilmBoDisplayer(Film film) { 
		this.id = film.getId();
		this.title = film.getTitle();
	}
}
