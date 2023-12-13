package com.maudits.website.repository.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import com.maudits.website.repository.entities.utils.EntityWithId;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Film extends EntityWithId {
	private String textualId;
	private String title;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private String posterUrl;
	private String thumbnailPosterUrl;
	private String sampleImageUrl;
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

	private LocalDateTime revealTime;

	private ZonedDateTime lastUpdateTime;

	@ManyToOne
	private Edition edition;

	public boolean isCurrentEdition() {
		return edition != null && edition.isCurrent();
	}

	public boolean isNextEdition() {
		return edition != null && edition.isNext();
	}
}
