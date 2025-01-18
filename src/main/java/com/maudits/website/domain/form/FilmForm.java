package com.maudits.website.domain.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Film;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FilmForm {
	private Long id;
	@Size(max = 100)
	private String textualId;
	@Size(max = 100)
	private String title;
	@Size(max = 3000)
	private String description;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime revealTime;

	@Size(max = 1000)
	private String posterUrl;
	private MultipartFile posterFile;
	@Size(max = 1000)
	private String sampleUrl;
	private MultipartFile sampleImageFile;

	@Size(max = 1000)
	private String releaseDate;
	@Size(max = 1000)
	private String director;
	@Size(max = 1000)
	private String scenarist;
	@Size(max = 100)
	private String nationality;
	@Size(max = 1000)
	private String duration;
	@Size(max = 1000)
	private String casting;
	@Size(max = 1000)
	private String partner;
	@Size(max = 1000)
	private String buyUrl;
	@Size(max = 1000)
	private String ageRating;
	@Size(max = 1000)
	private String locationName;
	@Size(max = 1000)
	private String locationAddress;
	@Size(max = 50)
	private String sponsor;
	@Size(max = 200)
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
