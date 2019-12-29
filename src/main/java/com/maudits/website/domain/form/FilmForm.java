package com.maudits.website.domain.form;

import java.time.LocalDate;
import java.time.LocalTime;

import com.maudits.website.repository.entities.Film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FilmForm {
	private Long id;
	private String title;
	private String description;
	private LocalDate date;
	private LocalTime startTime;

	public FilmForm(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.date = film.getDate();
		this.startTime = film.getStartTime();
	}
}
