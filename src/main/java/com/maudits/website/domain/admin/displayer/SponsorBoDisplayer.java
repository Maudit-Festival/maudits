package com.maudits.website.domain.admin.displayer;

import com.maudits.website.repository.entities.Sponsor;

import lombok.Getter;

@Getter
public class SponsorBoDisplayer {
	private final Long id;
	private final String name;
	private final String logoUrl;
	private final boolean targetUrlSet;

	public SponsorBoDisplayer(Sponsor sponsor) {
		this.id = sponsor.getId();
		this.name = sponsor.getName();
		this.logoUrl = sponsor.getLogoUrl();
		this.targetUrlSet = sponsor.getTargetUrl() != null && !sponsor.getTargetUrl().isBlank();
	}
}
