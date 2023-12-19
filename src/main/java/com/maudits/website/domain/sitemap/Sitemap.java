package com.maudits.website.domain.sitemap;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

@XmlAccessorType(value = XmlAccessType.NONE)
@XmlRootElement(name = "urlset")
@NoArgsConstructor
public class Sitemap {
	@XmlElements({ @XmlElement(name = "url", type = SitemapUrl.class) })
	private Collection<SitemapUrl> sitemapUrl = new ArrayList<SitemapUrl>();

	public Sitemap(Collection<SitemapUrl> sitemapUrl) {
		super();
		this.sitemapUrl = sitemapUrl;
	}
}
