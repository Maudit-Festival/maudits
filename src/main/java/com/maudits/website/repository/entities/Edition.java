package com.maudits.website.repository.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.maudits.website.repository.entities.utils.EntityWithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Edition extends EntityWithId {
	@OneToMany(mappedBy = "edition")
	private Collection<Film> films;
	@OneToMany(mappedBy = "edition")
	private Collection<Sponsor> sponsors;
	private boolean current;
	private boolean next;
	private String name;
}
