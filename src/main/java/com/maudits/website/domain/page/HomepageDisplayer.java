package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.FrontVisualInfo;
import com.maudits.website.domain.displayer.HomePageCurrentEventDisplayer;
import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;

import lombok.Getter;

@Getter
public class HomepageDisplayer extends FrontPageDisplayer {
	private final String editorial;
	private final String teaserVideoUrl;
	private final List<HomePageDayDisplayer> days;
	private final HomePageCurrentEventDisplayer currentEvent;
	private final List<SponsorDisplayer> sponsors;
	private final String pdfUrl;

	public HomepageDisplayer(FrontVisualInfo visualInfo, List<HomePageDayDisplayer> days,
			List<SponsorDisplayer> sponsors) {
		super(visualInfo);
		this.editorial = edition.getEditorial();
		this.teaserVideoUrl = edition.getTeaserUrl();
		this.days = days;
		this.currentEvent = null;
		this.sponsors = sponsors;
		this.pdfUrl = edition.getPdfUrl();
	}

	public HomepageDisplayer(FrontVisualInfo visualInfo, List<SponsorDisplayer> sponsors) {
		super(visualInfo);
		this.editorial = null;
		this.teaserVideoUrl = null;
		this.days = null;
		this.currentEvent = visualInfo.currentExtraEvent();
		this.sponsors = sponsors;
		this.pdfUrl = null;
	}
}
