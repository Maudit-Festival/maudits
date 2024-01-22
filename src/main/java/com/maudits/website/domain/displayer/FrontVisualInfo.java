package com.maudits.website.domain.displayer;

import java.util.List;
import java.util.Optional;

import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.ExtraEvent;

public record FrontVisualInfo(Optional<ExtraEvent> currentExtraEvent, Edition currentEdition,
		List<String> editionNames) {
	public FrontVisualInfo {
		if (currentExtraEvent.isPresent()) {
			editionNames.add(currentEdition.getName());
		}
	}
};
