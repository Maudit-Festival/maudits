package com.maudits.website.domain.page;

import java.util.ArrayList;
import java.util.List;

import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.domain.displayer.GuestDisplayer;
import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class HomePage extends FrontPage {
	private final List<HomePageDayDisplayer> days;
	private final HomePageCurrentEventDisplayer currentEvent;
	private final List<SponsorDisplayer> sponsors;
	private final List<GuestDisplayer> guests;
	private final String pdfUrl;

	public HomePage(FrontPageDisplayer displayer, List<HomePageDayDisplayer> days, List<SponsorDisplayer> sponsors) {
		super(displayer);
		this.days = days;
		this.currentEvent = null;
		this.sponsors = sponsors;
		Edition edition = displayer.getEdition();
		this.guests = edition.getGuests().stream().map(GuestDisplayer::new).toList();
		this.pdfUrl = edition.getPdfUrl();
	}

	public HomePage(FrontPageDisplayer displayer, HomePageCurrentEventDisplayer currentEvent,
			List<SponsorDisplayer> sponsors) {
		super(displayer);
		this.days = null;
		this.currentEvent = currentEvent;
		this.guests = new ArrayList<>();
		this.sponsors = sponsors;
		this.pdfUrl = null;
	}
}
