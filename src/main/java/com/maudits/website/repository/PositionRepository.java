package com.maudits.website.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Position;

public interface PositionRepository extends ListCrudRepository<Position, Long> {
	List<Position> findAllByOrderByPriorityAsc();
}
