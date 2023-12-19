package com.maudits.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.maudits.website.repository.entities.ExtraEvent;

public interface ExtraEventRepository extends CrudRepository<ExtraEvent, Long> {
	@Query("select ae from ExtraEvent ae where ae.revealTime < current_timestamp and ae.hideTime > current_timestamp")
	Optional<ExtraEvent> findOneByActive();

	@Query("select ae from ExtraEvent ae where ae.hideTime > current_timestamp")
	List<ExtraEvent> findFutureEvents();

	@Query("select ae from ExtraEvent ae where ae.hideTime < current_timestamp")
	List<ExtraEvent> findPastEvents();
}
