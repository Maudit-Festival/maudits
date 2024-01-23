package com.maudits.website.domain.form;

import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.PressLogo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PressLogoForm {
	private Long id;
	private String name;
	private String description;

	private String logoUrl;
	private MultipartFile logoFile;

	public PressLogoForm(PressLogo pressLogo) {
		this.id = pressLogo.getId();
		this.name = pressLogo.getName();
		this.description = pressLogo.getDescription();
		this.logoUrl = pressLogo.getLogoUrl();
	}
}
