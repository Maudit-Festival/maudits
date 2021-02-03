package com.maudits.website.domain.page;

import java.util.List;
import java.util.stream.Collectors;

import com.maudits.website.domain.displayer.EditionDisplayer;
import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class ArchivePageDisplayer extends FrontPageDisplayer {
	private final List<EditionDisplayer> editions;

	public ArchivePageDisplayer(Edition currentEdition, List<Edition> pastEditions) {
		super(currentEdition);
		this.editions = pastEditions.stream().map(EditionDisplayer::new).collect(Collectors.toUnmodifiableList());
	}
}
