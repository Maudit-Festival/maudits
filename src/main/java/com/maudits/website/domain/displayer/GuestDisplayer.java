package com.maudits.website.domain.displayer;

import com.maudits.website.repository.entities.Guest;

import lombok.Getter;

@Getter
public class GuestDisplayer {
	private final String name;
	private final String description;
	private final String imageUrl;

	public GuestDisplayer(Guest guest) {
		this.name = guest.getName();
		this.description = guest.getDescription();
		this.imageUrl = guest.getImageUrl();
	}

}
