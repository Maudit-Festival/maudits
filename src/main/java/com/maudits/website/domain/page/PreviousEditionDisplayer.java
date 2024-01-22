package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.FrontVisualInfo;
import com.maudits.website.domain.displayer.GuestDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class PreviousEditionDisplayer extends FrontPageDisplayer {

	private final String name;
	private final String editorial;
	private final String teaserVideoUrl;
	private final List<HomePageDayDisplayer> days;
	private final List<GuestDisplayer> guests;

	public PreviousEditionDisplayer(FrontVisualInfo visualInfo, Edition shownEdition, List<HomePageDayDisplayer> days) {
		super(visualInfo);

		this.name = shownEdition.getName();
		this.editorial = shownEdition.getEditorial();
		this.teaserVideoUrl = shownEdition.getTeaserUrl();
		this.days = days;
		this.guests = shownEdition.getGuests().stream().map(GuestDisplayer::new).toList();
	}
}
