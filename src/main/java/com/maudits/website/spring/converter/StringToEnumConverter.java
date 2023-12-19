package com.maudits.website.spring.converter;

import org.springframework.core.convert.converter.Converter;

import com.maudits.website.domain.DisplayEdition;

public class StringToEnumConverter implements Converter<String, DisplayEdition> {
	@Override
	public DisplayEdition convert(String source) {
		return DisplayEdition.valueOf(source.toUpperCase());
	}
}