package com.maudits.website.repository;

import org.springframework.data.repository.CrudRepository;

import com.maudits.website.repository.entities.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
