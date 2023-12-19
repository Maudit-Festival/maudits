package com.maudits.website.service;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.entities.Edition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentEditionService {
	private final EditionRepository editionRepository;

	public Edition findEdition(DisplayEdition displayEdition) {
		return switch (displayEdition) {
		case CURRENT -> editionRepository.findOneByCurrentTrue();
		case NEXT -> editionRepository.findOneByNextTrue();
		default -> throw new IllegalArgumentException("Unexpected value: " + displayEdition);
		};
	}

}
