package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class HomepageDisplayer extends FrontPageDisplayer {
	private final List<HomePageDayDisplayer> days;
	private final HomePageCurrentEventDisplayer currentEvent;
	private final List<SponsorDisplayer> sponsors;

	public HomepageDisplayer(Edition edition, List<String> editionNames, List<HomePageDayDisplayer> days,
			List<SponsorDisplayer> sponsors) {
		super(edition, editionNames);
		this.days = days;
		this.currentEvent = null;
		this.sponsors = sponsors;
	}

	public HomepageDisplayer(Edition edition, List<String> editionNames, HomePageCurrentEventDisplayer currentEvent,
			List<SponsorDisplayer> sponsors) {
		super(edition, editionNames);
		this.days = null;
		this.currentEvent = currentEvent;
		this.sponsors = sponsors;
	}
}
