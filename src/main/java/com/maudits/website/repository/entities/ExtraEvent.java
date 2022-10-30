package com.maudits.website.repository.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.maudits.website.repository.entities.utils.EntityWithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExtraEvent extends EntityWithId {
	private LocalDateTime revealTime;
	private LocalDateTime hideTime;
	@OneToOne(cascade = CascadeType.ALL)
	private Film film;
}
