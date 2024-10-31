package com.maudits.website.domain.admin.displayer;

import com.maudits.website.repository.entities.PressLogo;

import lombok.Getter;

@Getter
public class PressLogoDisplayer {
	private final Long id;
	private final String name;
	private final String logoUrl;

	public PressLogoDisplayer(PressLogo pressLogo) {
		this.id = pressLogo.getId();
		this.name = pressLogo.getName();
		this.logoUrl = pressLogo.getLogoUrl();
	}
}
