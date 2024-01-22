package com.maudits.website.domain.page;

import com.maudits.website.domain.displayer.FilmDetailDisplayer;
import com.maudits.website.domain.displayer.FrontVisualInfo;
import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailPageDisplayer extends FrontPageDisplayer {
	private final FilmDetailDisplayer filmDisplayer;

	public FilmDetailPageDisplayer(FrontVisualInfo visualInfo, Film film) {
		super(visualInfo);
		this.filmDisplayer = new FilmDetailDisplayer(film);
	}
}
