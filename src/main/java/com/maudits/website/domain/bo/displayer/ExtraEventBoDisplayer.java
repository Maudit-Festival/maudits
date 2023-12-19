package com.maudits.website.domain.bo.displayer;

import com.maudits.website.repository.entities.ExtraEvent;

import lombok.Getter;

@Getter
public class ExtraEventBoDisplayer extends FilmBoDisplayer {
	private final Long eventId;

	public ExtraEventBoDisplayer(ExtraEvent extraEvent) {
		super(extraEvent.getFilm());
		this.eventId = extraEvent.getId();
	}
}
