package com.maudits.website.domain.page;

import com.maudits.website.domain.displayer.FilmDetailDisplayer;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailPageDisplayer extends FrontPageDisplayer {
	private final FilmDetailDisplayer filmDisplayer;

	public FilmDetailPageDisplayer(Edition edition, Film film) {
		super(edition);
		this.filmDisplayer = new FilmDetailDisplayer(film);
	}
}
