package com.maudits.website.domain.admin.displayer;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditionBoDisplayer {
	private final List<FilmBoDisplayer> films;
	private final List<SponsorBoDisplayer> sponsors;
	private final List<GuestBoDisplayer> guests;
	private final List<PositionBoDisplayer> positions;
}
