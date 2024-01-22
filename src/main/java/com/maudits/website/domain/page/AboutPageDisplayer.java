package com.maudits.website.domain.page;

import com.maudits.website.domain.displayer.FrontVisualInfo;

import lombok.Getter;

@Getter
public class AboutPageDisplayer extends FrontPageDisplayer {
//	private final String pdfUrl;

	public AboutPageDisplayer(FrontVisualInfo visualInfo) {
		super(visualInfo);
//		this.pdfUrl = edition.getPdfUrl();
	}
}
