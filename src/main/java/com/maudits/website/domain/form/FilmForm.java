package com.maudits.website.domain.form;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FilmForm {
	private Long id;
	private String textualId;
	private String title;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;

	private String posterUrl;
	private MultipartFile posterFile;
	private String sampleUrl;
	private MultipartFile sampleImageFile;

	private String releaseDate;
	private String director;
	private String scenarist;
	private String nationality;
	private String duration;
	private String casting;
	private String partner;
	private String buyUrl;
	private String ageRating;
	private String locationName;
	private String locationAddress;

	private boolean nextEdition;

	public FilmForm(Film film) {
		this.id = film.getId();
		this.textualId = film.getTextualId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.date = film.getDate();
		this.startTime = film.getStartTime();

		this.releaseDate = film.getReleaseDate();
		this.director = film.getDirector();
		this.scenarist = film.getScenarist();
		this.nationality = film.getNationality();
		this.duration = film.getDuration();
		this.casting = film.getCasting();
		this.partner = film.getPartner();
		this.buyUrl = film.getBuyUrl();
		this.ageRating = film.getAgeRating();
		this.locationName = film.getLocationName();
		this.locationAddress = film.getLocationAddress();

		this.posterUrl = film.getPosterUrl();
		this.sampleUrl = film.getSampleImageUrl();

		this.nextEdition = film.isNextEdition();
	}

	public FilmForm(boolean nextEdition) {
		super();
		this.nextEdition = nextEdition;
	}
}
