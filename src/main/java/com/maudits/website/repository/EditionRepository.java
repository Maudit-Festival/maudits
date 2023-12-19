package com.maudits.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Edition;

public interface EditionRepository extends ListCrudRepository<Edition, Long> {

	Edition findOneByCurrentTrue();

	Edition findOneByNextTrue();

	List<Edition> findAllByCurrentFalseAndNextFalse();

	Optional<Edition> findByName(String editionCode);

}
