package com.maudits.website.domain.sitemap;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

@XmlAccessorType(value = XmlAccessType.NONE)
@XmlRootElement(name = "url")
@NoArgsConstructor
public class SitemapUrl {

	public enum Priority {
		HIGH("1.0"), MEDIUM("0.5");

		private String value;

		Priority(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	@XmlElement
	private String loc;

	@XmlElement
	private String lastmod;

	@XmlElement
	private String changefreq;

	@XmlElement
	private String priority;

	public SitemapUrl(String loc, Priority priority) {
		this.loc = loc;
		this.priority = priority.getValue();
	}

	public SitemapUrl(String loc, Priority priority, ZonedDateTime lastModified) {
		this.loc = loc;
		this.priority = priority.getValue();
		this.lastmod = lastModified.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}
