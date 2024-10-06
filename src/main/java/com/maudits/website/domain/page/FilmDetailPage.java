package com.maudits.website.domain.page;

import com.maudits.website.domain.displayer.FilmDetailDisplayer;
import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailPage extends FrontPage {
	private final FilmDetailDisplayer filmDisplayer;

	public FilmDetailPage(FrontPageDisplayer frontPageDisplayer, Film film) {
		super(frontPageDisplayer);
		this.filmDisplayer = new FilmDetailDisplayer(film);
	}
}
