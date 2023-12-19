package com.maudits.website.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Sponsor;

public interface SponsorRepository extends ListCrudRepository<Sponsor, Long> {

}
