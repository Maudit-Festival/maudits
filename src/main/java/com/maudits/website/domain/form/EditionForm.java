package com.maudits.website.domain.form;

import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Edition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditionForm {
	private String color;
	private String editorial;
	private String name;
	private String timePeriod;
	private String heroUrl;
	private MultipartFile heroFile;

	private String teaserUrl;
	// TODO guests

	public EditionForm(Edition edition) {
		this.color = edition.getAccentColor();
		this.editorial = edition.getEditorial();
		this.name = edition.getName();
		this.timePeriod = edition.getTimePeriod();
		this.teaserUrl = edition.getTeaserUrl();
		this.heroUrl = edition.getHeroUrl();
	}
}