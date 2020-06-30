package com.maudits.website.repository.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;

import com.maudits.website.repository.entities.utils.EntityWithId;

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
	private String duration;
	private String casting;
	private String partner;
	private String buyUrl;
	private String ageRating;
	private String locationName;
	private String locationAddress;
}
