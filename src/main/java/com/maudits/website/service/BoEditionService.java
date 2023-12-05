package com.maudits.website.service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.domain.bo.displayer.EditionBoDisplayer;
import com.maudits.website.domain.bo.displayer.FilmBoDisplayer;
import com.maudits.website.domain.bo.displayer.SponsorBoDisplayer;
import com.maudits.website.domain.form.EditionForm;
import com.maudits.website.repository.BoothPictureRepository;
import com.maudits.website.repository.EditionRepository;
import com.maudits.website.repository.entities.BoothPicture;
import com.maudits.website.repository.entities.Edition;
import com.maudits.website.repository.entities.Film;
import com.maudits.website.repository.entities.Sponsor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoEditionService {
	private final CurrentEditionService currentEditionService;
	private final UploadService uploadService;
	private final EditionRepository editionRepository;
	private final BoothPictureRepository boothPictureRepository;

	private List<FilmBoDisplayer> findFilms(Edition edition) {
		List<FilmBoDisplayer> result = new ArrayList<>();
		for (Film film : edition.getFilms()) {
			result.add(new FilmBoDisplayer(film));
		}
		return result;
	}

	private List<SponsorBoDisplayer> findSponsors(Edition edition) {
		List<SponsorBoDisplayer> result = new ArrayList<>();
		for (Sponsor sponsor : edition.getSponsors()) {
			result.add(new SponsorBoDisplayer(sponsor));
		}
		return result;
	}

	public EditionBoDisplayer buildDisplayer(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		return new EditionBoDisplayer(findFilms(edition), findSponsors(edition));
	}

	public EditionForm buildForm(DisplayEdition displayEdition) {
		Edition edition = currentEditionService.findEdition(displayEdition);
		return new EditionForm(edition);
	}

	private String nullIfEmpty(String string) {
		return (string != null && !string.isBlank()) ? string : null;
	}

	public void saveEdition(DisplayEdition displayEdition, @Valid EditionForm form) throws IOException {
		Edition edition = currentEditionService.findEdition(displayEdition);
		edition.setAccentColor(form.getColor());
		edition.setEditorial(nullIfEmpty(form.getEditorial()));
		edition.setName(form.getName());
		edition.setTimePeriod(form.getTimePeriod());
		edition.setTeaserUrl(nullIfEmpty(form.getTeaserUrl()));

		String folder = edition.getName();

		var heroFile = form.getHeroFile();
		if (!heroFile.isEmpty()) {
			var tmp = heroFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "hero" + fileExtension, heroFile);
			edition.setHeroUrl(url);
		}

		var shareImageFile = form.getShareImageFile();
		if (!shareImageFile.isEmpty()) {
			var tmp = shareImageFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "share" + fileExtension, shareImageFile);
			edition.setShareImageUrl(url);
		}

		var pdfFile = form.getPdfFile();
		if (!pdfFile.isEmpty()) {
			var tmp = pdfFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "programme_maudit_festival_" + edition.getName() + fileExtension,
					pdfFile);
			edition.setPdfUrl(url);
		}

		editionRepository.save(edition);
	}

	@Transactional
	public void makeEditionCurrent() {
		Edition previousCurrentEdition = currentEditionService.findEdition(DisplayEdition.CURRENT);
		Edition previousNextEdition = currentEditionService.findEdition(DisplayEdition.NEXT);
		Edition newNextEdition = new Edition();

		previousCurrentEdition.setCurrent(false);
		editionRepository.save(previousCurrentEdition);

		previousNextEdition.setNext(false);
		previousNextEdition.setCurrent(true);
		editionRepository.save(previousNextEdition);

		newNextEdition.setName("");
		newNextEdition.setTimePeriod("");
		newNextEdition.setLastUpdateTime(ZonedDateTime.now());
		newNextEdition.setNext(true);
		editionRepository.save(newNextEdition);
	}

	public void editionPicturesSave(String password, List<MultipartFile> files) throws IOException {
		Edition edition = currentEditionService.findEdition(DisplayEdition.CURRENT);
		edition.setBoothPicturesPassword(password);
		editionRepository.save(edition);

		String folder = edition.getName() + "/booth-pictures";
		for (MultipartFile file : files) {
			var url = uploadService.uploadFile(folder, FilenameUtils.getName(file.getOriginalFilename()), file);
			boothPictureRepository.save(new BoothPicture(url, edition));
		}
	}
}
