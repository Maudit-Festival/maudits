package com.maudits.website.domain.bo.displayer;

import java.util.List;

import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Position;

import lombok.Getter;

@Getter
public class PositionBoDisplayer {
	private final Long id;
	private final String name;
	private final List<CrewBoDisplayer> crews;

	public PositionBoDisplayer(Position position, List<Crew> crews) {
		this.id = position.getId();
		this.name = position.getName();
		this.crews = crews.stream().map(CrewBoDisplayer::new).toList();
	}
}
