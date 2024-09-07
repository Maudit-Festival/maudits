package com.maudits.website.service;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.form.CrewForm;
import com.maudits.website.repository.CrewRepository;
import com.maudits.website.repository.PositionRepository;
import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Position;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoCrewService {
	private final CurrentEditionService currentEditionService;
	private final PositionRepository positionRepository;
	private final CrewRepository crewRepository;

	public void saveCrew(DisplayEdition displayEdition, CrewForm form) {
		Position position = positionRepository.findById(form.getPositionId()).orElseThrow();
		Edition edition = currentEditionService.findEdition(displayEdition);
		crewRepository.save(new Crew(form.getFirstName(), form.getLastName(), position, edition));
	}

	public void editCrew(DisplayEdition displayEdition, Long crewId, CrewForm form) {
		Position position = positionRepository.findById(form.getPositionId()).orElseThrow();
		Edition edition = currentEditionService.findEdition(displayEdition);
		Crew crew = crewRepository.findById(crewId).orElseThrow();
		if (position.getId() != crew.getPosition().getId() || edition.getId() != crew.getEdition().getId()) {
			throw new RuntimeException("Incoherent information");
		}
		crew.setFirstName(form.getFirstName());
		crew.setLastName(form.getLastName());
		crewRepository.save(crew);
	}

	public void deleteCrew(DisplayEdition edition, Long crewId) {
		Crew crew = crewRepository.findById(crewId).orElseThrow();
		crewRepository.delete(crew);
	}

}
