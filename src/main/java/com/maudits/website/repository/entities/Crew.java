package com.maudits.website.repository.entities;

import com.maudits.website.repository.entities.utils.EntityWithId;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crew extends EntityWithId {
	private String firstName;
	private String lastName;

	@ManyToOne
	private Position position;
	@ManyToOne
	private Edition edition;
}
