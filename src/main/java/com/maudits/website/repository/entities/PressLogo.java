package com.maudits.website.repository.entities;

import com.maudits.website.repository.entities.utils.EntityWithId;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PressLogo extends EntityWithId {
	private String name;
	private String description;
	private String logoUrl;
}
