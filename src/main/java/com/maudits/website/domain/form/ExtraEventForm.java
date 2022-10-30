package com.maudits.website.domain.form;

import com.maudits.website.repository.entities.ExtraEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExtraEventForm extends FilmForm {
	private Long eventId;

	public ExtraEventForm(ExtraEvent event) {
		super(event.getFilm());
		this.eventId = event.getId();
	}
}
