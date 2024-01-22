package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.FrontVisualInfo;

import lombok.Getter;

@Getter
public class FrontPageDisplayer {
	private final String editionTimePeriod;
	private final String editionName;
//	private final String editorialTitle;
	private final boolean editorialAvailable;
	private final String shareImageUrl;
	private final String heroUrl;
	private final String accentColor;
	private final String root;
	private final List<String> previousEditionNames;

	public FrontPageDisplayer(FrontVisualInfo visualInfo) {
		this.editionTimePeriod = visualInfo.currentEdition().getTimePeriod();
		this.editionName = visualInfo.currentEdition().getName();
//		this.editorialTitle = edition.getEditorialTitle();
		this.editorialAvailable = visualInfo.currentExtraEvent().isEmpty()
				? visualInfo.currentEdition().getEditorial() != null
						&& !visualInfo.currentEdition().getEditorial().isBlank()
				: false;
		this.shareImageUrl = visualInfo.currentEdition().getShareImageUrl();
		this.heroUrl = visualInfo.currentEdition().getHeroUrl();
		this.accentColor = visualInfo.currentEdition().getAccentColor();
		this.root = visualInfo.currentEdition().isNext() ? "/bo/next/" : "/";
		this.previousEditionNames = visualInfo.editionNames();
	}

}
