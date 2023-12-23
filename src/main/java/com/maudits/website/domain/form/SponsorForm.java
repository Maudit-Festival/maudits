package com.maudits.website.domain.form;

import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Sponsor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SponsorForm {
	private Long id;
	private String name;
	private String textualId;
	private String targetUrl;

	private String logoUrl;
	private MultipartFile logoFile;

	public SponsorForm(Sponsor sponsor) {
		this.id = sponsor.getId();
		this.name = sponsor.getName();
		this.textualId = sponsor.getTextualId();
		this.logoUrl = sponsor.getLogoUrl();
		this.targetUrl = sponsor.getTargetUrl();
	}
}
