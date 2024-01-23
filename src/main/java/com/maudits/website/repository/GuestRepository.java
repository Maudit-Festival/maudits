package com.maudits.website.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.maudits.website.repository.entities.Guest;

public interface GuestRepository extends ListCrudRepository<Guest, Long> {
}
