package com.maudits.website.spring.converter;

import org.springframework.core.convert.converter.Converter;

import com.maudits.website.domain.Display;

public class StringToEnumConverter implements Converter<String, Display> {
	@Override
	public Display convert(String source) {
		return Display.valueOf(source.toUpperCase());
	}
}