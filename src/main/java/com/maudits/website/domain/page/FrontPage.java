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
	private final boolean hasEditorial;
	private final String shareImageUrl;
	private final String heroUrl;
	private final String accentColor;
	private final String buyPassUrl;
	private final boolean preview;
	private final boolean pastEdition;
	private final List<String> previousEditionNames;

	public FrontPage(FrontPageDisplayer displayer) {
		this(displayer, displayer.getEdition());
	}

	public FrontPage(FrontPageDisplayer displayer, Edition shownEdition) {
		Edition currentEdition = displayer.getEdition();
		Display display = displayer.getDisplay();
		this.editionTimePeriod = currentEdition.getTimePeriod();
		this.editionName = currentEdition.getName();
		this.hasEditorial = currentEdition.getEditorial() != null && !currentEdition.getEditorial().isBlank();
		this.shareImageUrl = currentEdition.getShareImageUrl();
		this.heroUrl = shownEdition.getHeroUrl();
		this.accentColor = shownEdition.getAccentColor();
		this.buyPassUrl = currentEdition.getBuyPassUrl();
		this.preview = display == Display.NEXT;
		boolean showcasingCurrentEdition = display == Display.NEXT ? currentEdition.isNext()
				: currentEdition.isCurrent();
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
