package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class AboutPageDisplayer extends FrontPageDisplayer {
	private final String pdfUrl;

	public AboutPageDisplayer(Edition edition, List<String> editionNames) {
		super(edition, editionNames);
		this.pdfUrl = edition.getPdfUrl();
	}
}
