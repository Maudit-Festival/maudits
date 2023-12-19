package com.maudits.website.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import com.maudits.website.repository.entities.utils.EntityWithId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoothPicture extends EntityWithId {
	private String imageUrl;

	@ManyToOne
	private Edition edition;
}
