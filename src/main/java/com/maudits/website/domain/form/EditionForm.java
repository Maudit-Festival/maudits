package com.maudits.website.domain.form;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditionForm {
	private String color;
	private String editorial;
	private String name;
	private String timePeriod;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate firstDay;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate lastDay;
	private String shareImageUrl;
	private MultipartFile shareImageFile;
	private String heroUrl;
	private MultipartFile heroFile;

	private String buyPassUrl;

	private String pdfUrl;
	private MultipartFile pdfFile;

	private String teaserUrl;

	private String tickets;

	public EditionForm(Edition edition) {
		this.color = edition.getAccentColor();
		this.editorial = edition.getEditorial();
		this.name = edition.getName();
		this.timePeriod = edition.getTimePeriod();
		this.firstDay = edition.getFirstDay();
		this.lastDay = edition.getLastDay();
		this.shareImageUrl = edition.getShareImageUrl();
		this.heroUrl = edition.getHeroUrl();
		this.pdfUrl = edition.getPdfUrl();
		this.teaserUrl = edition.getTeaserUrl();
		this.buyPassUrl = edition.getBuyPassUrl();
		this.tickets = edition.getTickets().stream().map(Ticket::getText).collect(Collectors.joining("\n"));
	}
}
