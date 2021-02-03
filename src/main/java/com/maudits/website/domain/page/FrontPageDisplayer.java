package com.maudits.website.domain.page;

import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class FrontPageDisplayer {
	private final String editionTimePeriod;
	private final String editionName;

	public FrontPageDisplayer(Edition edition) {
		this.editionTimePeriod = edition.getTimePeriod();
		this.editionName = edition.getName();
	}
}
