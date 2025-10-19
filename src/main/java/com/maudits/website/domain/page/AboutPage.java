package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.EditionRoleDisplayer;
import com.maudits.website.domain.displayer.FrontPageDisplayer;

import lombok.Getter;

@Getter
public class AboutPage extends FrontPage {
	private final String pdfUrl;
	private final List<EditionRoleDisplayer> credits;

	public AboutPage(FrontPageDisplayer displayer, List<EditionRoleDisplayer> credits) {
		super(displayer);
		this.pdfUrl = displayer.getEdition().getPdfUrl();
		this.credits = credits;
	}
}
