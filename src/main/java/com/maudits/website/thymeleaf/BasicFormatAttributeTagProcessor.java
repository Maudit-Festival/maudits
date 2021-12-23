package com.maudits.website.thymeleaf;

import org.thymeleaf.expression.Strings;

public class BasicFormatAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private static final String ATTR_NAME = "basic";

	public BasicFormatAttributeTagProcessor(final String dialectPrefix) {
		super(dialectPrefix, ATTR_NAME);
	}

	@Override
	protected String processString(String attributeContent) {
		String escapedString = new Strings(null).escapeXml(attributeContent);
		return escapedString.replace(System.getProperty("line.separator"), "<br />");
	}

}