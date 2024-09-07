package com.maudits.website.domain.bo.displayer;

import com.maudits.website.repository.entities.Crew;

import lombok.Getter;

@Getter
public class CrewBoDisplayer {
	private final Long id;
	private String firstName;
	private String lastName;

	public CrewBoDisplayer(Crew crew) {
		this.id = crew.getId();
		this.firstName = crew.getFirstName();
		this.lastName = crew.getLastName();
	}
}
