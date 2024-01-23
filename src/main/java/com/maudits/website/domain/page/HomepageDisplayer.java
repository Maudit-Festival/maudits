package com.maudits.website.domain.page;

import java.util.ArrayList;
import java.util.List;

import com.maudits.website.domain.displayer.GuestDisplayer;
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
	private final List<GuestDisplayer> guests;
	private final String pdfUrl;

	public HomepageDisplayer(Edition edition, List<String> editionNames, List<HomePageDayDisplayer> days,
			List<SponsorDisplayer> sponsors) {
		super(edition, editionNames);
		this.days = days;
		this.currentEvent = null;
		this.sponsors = sponsors;
		this.guests = edition.getGuests().stream().map(GuestDisplayer::new).toList();
		this.pdfUrl = edition.getPdfUrl();
	}

	public HomepageDisplayer(Edition edition, List<String> editionNames, HomePageCurrentEventDisplayer currentEvent,
			List<SponsorDisplayer> sponsors) {
		super(edition, editionNames);
		this.days = null;
		this.currentEvent = currentEvent;
		this.guests = new ArrayList<>();
		this.sponsors = sponsors;
		this.pdfUrl = null;
	}
}
