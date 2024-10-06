package com.maudits.website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.Display;
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

	public void saveCrew(Display displayEdition, CrewForm form) {
		Position position = positionRepository.findById(form.getPositionId()).orElseThrow();
		Edition edition = currentEditionService.findEdition(displayEdition);
		crewRepository.save(new Crew(form.getFirstName(), form.getLastName(), position, edition));
	}

	public void editCrew(Display displayEdition, Long crewId, CrewForm form) {
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

	public void deleteCrew(Display edition, Long crewId) {
		Crew crew = crewRepository.findById(crewId).orElseThrow();
		crewRepository.delete(crew);
	}

	public void addPosition(String positionName, Long previousPositionId) {
		List<Position> currentPositions = positionRepository.findAllByOrderByPriorityAsc();
		if (previousPositionId == null) {
			currentPositions.stream().forEach(p -> p.setPriority(p.getPriority() + 1));
			currentPositions.add(new Position(positionName, 0));
		} else {
			boolean afterTarget = false;
			int newPositionPriority = 0;
			for (Position position : currentPositions) {
				if (afterTarget) {
					position.setPriority(position.getPriority() + 1);
				}
				if (position.getId() == previousPositionId) {
					afterTarget = true;
					newPositionPriority = position.getPriority() + 1;
				}
			}
			currentPositions.add(new Position(positionName, newPositionPriority));
		}
		positionRepository.saveAll(currentPositions);
	}
}
