package com.maudits.website.repository.entities.utils;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode
public class EntityWithId {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}