package com.maudits.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Film;

public interface FilmRepository extends ListCrudRepository<Film, Long> {
	Optional<Film> findOneByTextualId(String textualId);

	List<Film> findAllByEditionNextFalse();
}
