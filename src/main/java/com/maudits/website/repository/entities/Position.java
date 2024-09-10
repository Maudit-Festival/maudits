package com.maudits.website.repository.entities;

import com.maudits.website.repository.entities.utils.EntityWithId;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`position`")
public class Position extends EntityWithId {
	private String name;
	private int priority;
}
