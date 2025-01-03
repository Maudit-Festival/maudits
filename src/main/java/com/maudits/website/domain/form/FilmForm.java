package com.maudits.website.domain.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime revealTime;

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
	private String sponsor;
	private String triggerWarnings;

	public FilmForm(Film film) {
		this.id = film.getId();
		this.textualId = film.getTextualId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.date = film.getDate();
		this.startTime = film.getStartTime();
		this.revealTime = film.getRevealTime();

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
		this.sponsor = film.getSponsor();
		this.triggerWarnings = film.getTriggerWarnings();

		this.posterUrl = film.getPosterUrl();
		this.sampleUrl = film.getSampleImageUrl();
	}
}
