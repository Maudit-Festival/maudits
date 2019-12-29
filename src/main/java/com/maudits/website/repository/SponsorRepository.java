package com.maudits.website.repository;

import org.springframework.data.repository.CrudRepository;

import com.maudits.website.repository.entities.Sponsor;

public interface SponsorRepository extends CrudRepository<Sponsor, Long> {

}
