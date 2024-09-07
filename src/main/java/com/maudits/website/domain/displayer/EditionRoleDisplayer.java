package com.maudits.website.domain.displayer;

import java.util.ArrayList;
import java.util.List;

import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Position;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditionRoleDisplayer {
	private final String name;
	private final List<String> crews;

	public EditionRoleDisplayer(Position position, List<Crew> crews) {
		this.name = position.getName();
		this.crews = new ArrayList<>();
		for (Crew crew : crews) {
			this.crews.add(crew.getFirstName() + " " + crew.getLastName());
		}
		this.crews.sort(null);
	}
}
