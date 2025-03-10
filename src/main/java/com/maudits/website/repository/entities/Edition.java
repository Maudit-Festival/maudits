package com.maudits.website.repository.entities;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import com.maudits.website.repository.entities.utils.EntityWithId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Edition extends EntityWithId {
	@OneToMany(mappedBy = "edition")
	private Collection<Film> films;
	@OneToMany(mappedBy = "edition")
	private Collection<Sponsor> sponsors;
	@OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Ticket> tickets;
	private boolean current;
	private boolean next;
	private String name;
	private String timePeriod;
	private LocalDate firstDay;
	private LocalDate lastDay;

	private String heroUrl;
	private String shareImageUrl;
	private String accentColor;

	private String editorialTitle;
	private String editorial;
	private String teaserUrl;
	private String pdfUrl;
	private String buyPassUrl;
	@OneToMany(mappedBy = "edition")
	private List<Guest> guests;

	private String boothPicturesPassword;
	@OneToMany(mappedBy = "edition")
	private List<BoothPicture> boothPictures;

	private ZonedDateTime lastUpdateTime;
}
