package com.maudits.website.domain.displayer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;

import lombok.Getter;

@Getter
public class EditionDisplayer {
	private final String name;
	private final String timePeriod;
	private final List<EditionFilm> films;

	public EditionDisplayer(Edition edition) {
		this.name = edition.getName();
		this.timePeriod = edition.getTimePeriod();
		this.films = edition.getFilms().stream().sorted(Comparator.comparing(Film::getDate)).map(EditionFilm::new)
				.collect(Collectors.toUnmodifiableList());
	}
}
