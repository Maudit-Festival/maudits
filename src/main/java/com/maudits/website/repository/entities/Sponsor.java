package com.maudits.website.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import com.maudits.website.repository.entities.utils.EntityWithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sponsor extends EntityWithId {
	private String name;
	private String logoUrl;
	private String targetUrl;

	@ManyToOne
	private Edition edition;
}
