package com.maudits.website.domain.form;

import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GuestForm {
	private Long id;
	private String name;
	private String description;

	private String pictureUrl;
	private MultipartFile pictureFile;

	public GuestForm(Guest film) {
		this.id = film.getId();
		this.name = film.getName();
		this.description = film.getDescription();
		this.pictureUrl = film.getImageUrl();
	}
}
