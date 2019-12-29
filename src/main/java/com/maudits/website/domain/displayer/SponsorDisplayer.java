package com.maudits.website.domain.displayer;

import com.maudits.website.repository.entities.Sponsor;

import lombok.Getter;

@Getter
public class SponsorDisplayer {
	private final String name;
	private final String logoUrl;
	private final String targetUrl;

	public SponsorDisplayer(Sponsor sponsor) {
		this.name = sponsor.getName();
		this.logoUrl = sponsor.getLogoUrl();
		this.targetUrl = sponsor.getTargetUrl();
	}
}
