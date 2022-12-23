package com.maudits.website.repository.entities;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.maudits.website.repository.entities.utils.EntityWithId;

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
	private boolean current;
	private boolean next;
	private String name;
	private String timePeriod;

	private String heroUrl;
	private String accentColor;

	private String editorialTitle;
	private String editorial;
	private String teaserUrl;
	private String pdfUrl;
	@OneToMany(mappedBy = "edition")
	private List<Guest> guests;

	private ZonedDateTime lastUpdateTime;
}
