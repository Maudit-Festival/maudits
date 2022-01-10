package com.maudits.website.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.maudits.website.domain.sitemap.Sitemap;
import com.maudits.website.domain.sitemap.SitemapUrl;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.FilmRepository;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SitemapService {
	private final FilmRepository filmRepository;
	private final EditionRepository editionRepository;

	private Collection<SitemapUrl> listStaticPages() {
		Collection<SitemapUrl> staticPagesUrls = new ArrayList<>();
		staticPagesUrls.add(new SitemapUrl("https://lemauditfestival.com", SitemapUrl.Priority.HIGH));
		staticPagesUrls.add(new SitemapUrl("https://lemauditfestival.com/contact", SitemapUrl.Priority.MEDIUM));
		staticPagesUrls.add(
				new SitemapUrl("https://lemauditfestival.com/a-propos-du-maudit-festival", SitemapUrl.Priority.MEDIUM));
		staticPagesUrls.add(new SitemapUrl("https://lemauditfestival.com/editions-precedentes/avant-le-maudit-festival",
				SitemapUrl.Priority.MEDIUM));

//		staticPagesUrls.add(
//				new SitemapUrl("https://lemauditfestival.com/a-propos-du-maudit-festival", SitemapUrl.Priority.HIGH));
//		staticPagesUrls.add(new SitemapUrl("https://lemauditfestival.com/editions-precedentes/avant-le-maudit-festival",
//				SitemapUrl.Priority.MEDIUM));

		return staticPagesUrls;
	}

	private Collection<SitemapUrl> listFilmPages() {
		Collection<SitemapUrl> filmPagesUrls = new ArrayList<>();
		for (Film film : filmRepository.findAllByEditionNextFalse()) {
			SitemapUrl.Priority priority = film.isCurrentEdition() ? SitemapUrl.Priority.HIGH
					: SitemapUrl.Priority.MEDIUM;
			filmPagesUrls.add(new SitemapUrl("https://lemauditfestival.com/film/" + film.getTextualId(), priority,
					film.getLastUpdateTime()));
		}
		return filmPagesUrls;
	}

	private Collection<SitemapUrl> listPastEditionPages() {
		Collection<SitemapUrl> filmPagesUrls = new ArrayList<>();
		for (Edition edition : editionRepository.findAllByCurrentFalseAndNextFalse()) {
			filmPagesUrls.add(new SitemapUrl("https://lemauditfestival.com/editions-precedentes/" + edition.getName(),
					SitemapUrl.Priority.MEDIUM, edition.getLastUpdateTime()));
		}
		return filmPagesUrls;
	}

	public Sitemap buildSitemap() {
		Collection<SitemapUrl> xmlUrlset = new ArrayList<>();
		xmlUrlset.addAll(listStaticPages());
		xmlUrlset.addAll(listPastEditionPages());
		xmlUrlset.addAll(listFilmPages());

		return new Sitemap(xmlUrlset);

	}
}
