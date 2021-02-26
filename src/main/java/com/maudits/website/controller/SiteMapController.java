package com.maudits.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maudits.website.domain.sitemap.Sitemap;
import com.maudits.website.service.SitemapService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SiteMapController {
	private final SitemapService sitemapService;

	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
	@ResponseBody
	public Sitemap main() {
		return sitemapService.buildSitemap();
	}

}
