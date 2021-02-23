package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class PreviousEditionDisplayer extends FrontPageDisplayer {
<<<<<<< HEAD
	private final String name;
=======
>>>>>>> a840b30a14ff4b73116a74b55a5f7482c006825e
	private final String editorial;
	private final String teaserVideoUrl;
	private final List<HomePageDayDisplayer> days;
	private final String guests;
	private final String partners;
	private final String places;
	private final String figures;
	private final String press;

	public PreviousEditionDisplayer(Edition edition, Edition shownEdition, List<HomePageDayDisplayer> days) {
		super(edition);
<<<<<<< HEAD
		this.name = shownEdition.getName();
=======
>>>>>>> a840b30a14ff4b73116a74b55a5f7482c006825e
		this.editorial = shownEdition.getEditorial();
		this.teaserVideoUrl = shownEdition.getTeaserUrl();
		this.days = days;
		this.guests = shownEdition.getGuests();
		this.partners = shownEdition.getPartners();
		this.places = shownEdition.getPlaces();
		this.figures = shownEdition.getFigures();
		this.press = shownEdition.getPress();
	}
}
