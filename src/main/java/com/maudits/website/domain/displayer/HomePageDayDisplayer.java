package com.maudits.website.domain.displayer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

@Getter
public class HomePageDayDisplayer {
	private final String displayDate;
	private final LocalDate date;
	private final List<HomePageFilmDisplayer> films;

	public HomePageDayDisplayer(LocalDate date, List<HomePageFilmDisplayer> films) {
		this.displayDate = date.format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRANCE));
		this.date = date;
		this.films = films;
	}
}
