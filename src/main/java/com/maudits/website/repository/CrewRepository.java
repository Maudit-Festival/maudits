package com.maudits.website.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Crew;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Position;

public interface CrewRepository extends ListCrudRepository<Crew, Long> {
	List<Crew> findAllByEdition(Edition edition);

	List<Crew> findAllByPositionAndEdition(Position position, Edition edition);
}
