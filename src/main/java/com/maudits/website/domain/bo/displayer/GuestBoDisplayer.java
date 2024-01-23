package com.maudits.website.domain.bo.displayer;

import com.maudits.website.repository.entities.Guest;

import lombok.Getter;

@Getter
public class GuestBoDisplayer {
	private final Long id;
	private final String name;
	private final String pictureUrl;

	public GuestBoDisplayer(Guest guest) {
		this.id = guest.getId();
		this.name = guest.getName();
		this.pictureUrl = guest.getImageUrl();
	}
}
