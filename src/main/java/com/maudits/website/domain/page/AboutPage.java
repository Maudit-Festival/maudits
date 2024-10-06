package com.maudits.website.domain.page;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.maudits.website.domain.displayer.EditionRoleDisplayer;
import com.maudits.website.domain.displayer.FrontPageDisplayer;
import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Position;

import lombok.Getter;

@Getter
public class AboutPage extends FrontPage {
	private final String pdfUrl;
	private final List<EditionRoleDisplayer> credits;

	public AboutPage(FrontPageDisplayer displayer, Map<Position, List<Crew>> credits) {
		super(displayer);
		this.pdfUrl = displayer.getEdition().getPdfUrl();
		List<EditionRoleDisplayer> creditDisplayers = new ArrayList<>();
		List<Position> positions = new ArrayList<>(credits.keySet());
		positions.sort(Comparator.comparing(c -> c.getPriority()));
		for (Position key : positions) {
			creditDisplayers.add(new EditionRoleDisplayer(key, credits.get(key)));
		}

		this.credits = creditDisplayers;
	}
}
