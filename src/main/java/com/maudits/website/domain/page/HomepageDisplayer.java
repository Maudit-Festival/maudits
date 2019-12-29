package com.maudits.website.domain.page;

import java.util.List;

import com.maudits.website.domain.displayer.HomePageDayDisplayer;
import com.maudits.website.domain.displayer.SponsorDisplayer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomepageDisplayer {
	private final List<HomePageDayDisplayer> days;
	private final List<SponsorDisplayer> sponsors;
}
