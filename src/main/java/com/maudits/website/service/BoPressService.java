package com.maudits.website.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.maudits.website.domain.bo.displayer.PressLogoDisplayer;
import com.maudits.website.domain.form.PressLogoForm;
import com.maudits.website.repository.PressLogoRepository;
import com.maudits.website.repository.entities.PressLogo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoPressService {
	private final PressLogoRepository pressLogoRepository;
	private final UploadService uploadService;

	public List<PressLogoDisplayer> findPressLogos() {
		return pressLogoRepository.findAll().stream().map(PressLogoDisplayer::new).toList();
	}

	public void uploadDocument(MultipartFile file) throws IOException {
		uploadService.uploadFile("press", "MauditFestival.pdf", file);
	}

	public PressLogoForm createPressLogoForm() {
		return new PressLogoForm();
	}

	public PressLogoForm findPressLogoFormFromId(Long id) {
		return new PressLogoForm(pressLogoRepository.findById(id).orElseThrow());
	}

	public void savePressLogo(PressLogoForm form) throws IOException {
		Long id = form.getId();
		PressLogo sponsor = (id != null) ? pressLogoRepository.findById(id).orElseThrow() : new PressLogo();
		sponsor.setName(form.getName());
		sponsor.setDescription(form.getDescription());

		var logoFile = form.getLogoFile();
		if (!logoFile.isEmpty()) {
			var url = uploadService.uploadFile("press", "" + System.currentTimeMillis(), logoFile);
			sponsor.setLogoUrl(url);
		}
		pressLogoRepository.save(sponsor);

	}

	public void deletePressLogo(Long id) {
		pressLogoRepository.deleteById(id);
	}

}
