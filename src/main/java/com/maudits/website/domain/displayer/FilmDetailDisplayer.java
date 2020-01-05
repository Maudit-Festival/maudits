package com.maudits.website.domain.displayer;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailDisplayer {
	private final String title;
	private final String description;
	private final String date;
	private final String startTime;
	private final String posterUrl;
	private final String sampleImageUrl;
	private final String releaseDate;
	private final String director;
	private final String scenarist;
	private final String casting;
	private final String duration;
	private final String partner;
	private final String buyUrl;
	private final String ageRating;

	public FilmDetailDisplayer(Film film) {
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.date = film.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRANCE));
		this.startTime = (film.getStartTime().getHour() != 0)
				? film.getStartTime().format(DateTimeFormatter.ofPattern("HH\'h\'"))
				: "Minuit";

		this.posterUrl = film.getThumbnailPosterUrl();
		this.sampleImageUrl = film.getSampleImageUrl();
		this.releaseDate = film.getReleaseDate();
		this.director = film.getDirector();
		this.scenarist = film.getScenarist();
		this.casting = film.getCasting();
		this.duration = film.getDuration();
		this.partner = film.getPartner();
		this.buyUrl = film.getBuyUrl();
		this.ageRating = film.getAgeRating();
	}
}
