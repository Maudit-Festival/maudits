package com.maudits.website.repository.entities;

import java.time.LocalDate;

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
public class Sponsor extends EntityWithId {
	private String name;
	private String textualId;
	private String logoUrl;
	private String targetUrl;

	private LocalDate date;
	@ManyToOne
	private Edition edition;
}
