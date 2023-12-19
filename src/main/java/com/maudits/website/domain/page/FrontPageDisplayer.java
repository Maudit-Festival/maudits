package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class FrontPageDisplayer {
	private final String editionTimePeriod;
	private final String editionName;
	private final String editorialTitle;
	private final String editorial;
	private final String teaserVideoUrl;
	private final String shareImageUrl;
	private final String heroUrl;
	private final String accentColor;
	private final String root;
	private final List<String> previousEditionNames;

	public FrontPageDisplayer(Edition edition, List<String> editionNames) {
		this.editionTimePeriod = edition.getTimePeriod();
		this.editionName = edition.getName();
		this.editorialTitle = edition.getEditorialTitle();
		this.editorial = edition.getEditorial();
		this.teaserVideoUrl = edition.getTeaserUrl();
		this.shareImageUrl = edition.getShareImageUrl();
		this.heroUrl = edition.getHeroUrl();
		this.accentColor = edition.getAccentColor();
		this.root = edition.isNext() ? "/bo/next/" : "/";
		this.previousEditionNames = editionNames;
	}
}
