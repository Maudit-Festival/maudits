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
public class Ticket extends EntityWithId {
	private String text;

	@ManyToOne
	private Edition edition;
}
