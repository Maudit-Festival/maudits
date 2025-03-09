package com.maudits.website.domain.displayer;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class HomePageEventDisplayer {
	private final Long id;
	private final String displayDate;
	private final String title;
	private final String textualId;

	public HomePageEventDisplayer(Film film) {
		this.id = film.getId();
		this.textualId = film.getTextualId();
		this.title = film.getTitle();
		this.displayDate = film.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRANCE));
	}
}
