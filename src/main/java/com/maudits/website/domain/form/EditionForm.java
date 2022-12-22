package com.maudits.website.domain.form;

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

	public EditionForm(Edition edition) {
		this.color = edition.getAccentColor();
		this.editorial = edition.getEditorial();
	}
}
