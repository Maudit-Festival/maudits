package com.maudits.website.repository.entities.utils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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