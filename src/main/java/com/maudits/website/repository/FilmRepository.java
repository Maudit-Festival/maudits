package com.maudits.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.maudits.website.repository.entities.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
	Optional<Film> findOneByTextualId(String textualId);

	List<Film> findAllByEditionNextFalse();
}
