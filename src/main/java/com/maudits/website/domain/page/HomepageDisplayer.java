package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class HomepageDisplayer extends FrontPageDisplayer {
	private final List<HomePageDayDisplayer> days;
	private final List<SponsorDisplayer> sponsors;

	public HomepageDisplayer(Edition edition, List<HomePageDayDisplayer> days, List<SponsorDisplayer> sponsors) {
		super(edition);
		this.days = days;
		this.sponsors = sponsors;
	}
}
