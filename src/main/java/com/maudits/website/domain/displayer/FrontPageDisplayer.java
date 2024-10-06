package com.maudits.website.domain.displayer;

import java.util.List;

import com.maudits.website.domain.Display;
import com.maudits.website.repository.entities.Edition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FrontPageDisplayer {
	private final Display display;
	private final Edition edition;
	private final List<String> previousEditionsNames;
}
