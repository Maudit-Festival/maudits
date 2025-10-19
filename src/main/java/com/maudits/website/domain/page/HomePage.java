package com.maudits.website.domain.page;

import java.util.ArrayList;
import java.util.List;

import com.maudits.website.domain.displayer.EditionRoleDisplayer;
import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.domain.displayer.GuestDisplayer;
import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.HomePageEventDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Ticket;

import lombok.Getter;

@Getter
public class HomePage extends FrontPage {
	private final List<HomePageEventDisplayer> beforeEvents;
	private final List<HomePageEventDisplayer> afterEvents;
	private final List<HomePageDayDisplayer> days;
	private final HomePageCurrentEventDisplayer currentEvent;
	private final List<SponsorDisplayer> sponsors;
	private final List<GuestDisplayer> guests;
	private final List<String> tickets;
	private final String pdfUrl;
	private final String posterUrl;
	private final List<EditionRoleDisplayer> credits;

	public HomePage(FrontPageDisplayer displayer, List<HomePageEventDisplayer> beforeEvents,
			List<HomePageEventDisplayer> afterEvents, List<HomePageDayDisplayer> days, List<SponsorDisplayer> sponsors,
			List<EditionRoleDisplayer> credits) {
		super(displayer);
		this.beforeEvents = beforeEvents;
		this.afterEvents = afterEvents;
		this.days = days;
		this.currentEvent = null;
		this.sponsors = sponsors;
		Edition edition = displayer.getEdition();
		this.guests = edition.getGuests().stream().map(GuestDisplayer::new).toList();
		this.tickets = edition.getTickets().stream().map(Ticket::getText).toList();
		this.pdfUrl = edition.getPdfUrl();
		this.posterUrl = edition.getPosterUrl();
		this.credits = credits;
	}

	public HomePage(FrontPageDisplayer displayer, HomePageCurrentEventDisplayer currentEvent,
			List<SponsorDisplayer> sponsors) {
		super(displayer);
		this.days = null;
		this.beforeEvents = null;
		this.afterEvents = null;
		this.currentEvent = currentEvent;
		this.guests = new ArrayList<>();
		this.sponsors = sponsors;
		this.tickets = new ArrayList<>();
		this.pdfUrl = null;
		this.posterUrl = null;
		this.credits = null;
	}
}
