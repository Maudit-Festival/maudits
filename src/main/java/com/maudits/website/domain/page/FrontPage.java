package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class FrontPage {
	private final String editionTimePeriod;
	private final String editionName;
	private final String editorialTitle;
	private final String editorial;
	private final String teaserVideoUrl;
	private final String shareImageUrl;
	private final String heroUrl;
	private final String accentColor;
//	private final String basePrefix;
//	private final String editionPrefix;
//	private final String homepageUrl;
	private final String buyPassUrl;
	private final boolean preview;
	private final boolean pastEdition;
	private final List<String> previousEditionNames;

	public FrontPage(FrontPageDisplayer displayer) {
		Edition edition = displayer.getEdition();
		Display display = displayer.getDisplay();
		this.editionTimePeriod = edition.getTimePeriod();
		this.editionName = edition.getName();
		this.editorialTitle = edition.getEditorialTitle();
		this.editorial = edition.getEditorial();
		this.teaserVideoUrl = edition.getTeaserUrl();
		this.shareImageUrl = edition.getShareImageUrl();
		this.heroUrl = edition.getHeroUrl();
		this.accentColor = edition.getAccentColor();
		this.buyPassUrl = edition.getBuyPassUrl();
		this.preview = display == Display.NEXT;
		boolean showcasingCurrentEdition = display == Display.NEXT ? edition.isNext() : edition.isCurrent();
		this.pastEdition = !(showcasingCurrentEdition);
		this.previousEditionNames = displayer.getPreviousEditionsNames();
	}

	public String getCurrentRoot() {
		return preview ? "/admin/next" : "/";
	}

//	private String addFinalSeparator(String string) {
//		return string.endsWith("/") ? string : string + "/";
//	}
//
//	public String getCurrentEditionHomepageUrl() {
//		return pastEdition ? addFinalSeparator(getCurrentRoot()) + "editions-precedentes/" + editionName
//				: getCurrentRoot();
//	}
//
//	public String getCurrentEditionPrefix() {
//		return addFinalSeparator(getCurrentEditionHomepageUrl());
//	}
//
//	public String findEditionHomepageUrlByName(String editionName) {
//		return addFinalSeparator(getCurrentRoot()) + "editions-precedentes/" + editionName;
//	}
}
