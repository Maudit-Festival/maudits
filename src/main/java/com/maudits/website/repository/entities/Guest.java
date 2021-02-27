package com.maudits.website.repository.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.maudits.website.repository.entities.utils.EntityWithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Guest extends EntityWithId {
	private String name;
	private String description;
	private String imageUrl;

	@ManyToOne
	private Edition edition;
}
