package com.maudits.website.domain.bo.displayer;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditionBoDisplayer {
	private final List<FilmBoDisplayer> films;
	private final List<SponsorBoDisplayer> sponsors;
}
