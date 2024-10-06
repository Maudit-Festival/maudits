package com.maudits.website.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.form.GuestForm;
import com.maudits.website.repository.GuestRepository;
import com.maudits.website.repository.entities.Guest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoGuestService {
	private final CurrentEditionService currentEditionService;
	private final GuestRepository guestRepository;
	private final UploadService uploadService;

	public GuestForm createGuestForm() {
		return new GuestForm();
	}

	public GuestForm findGuestFormFromId(Long id) {
		return new GuestForm(guestRepository.findById(id).orElseThrow());
	}

	public Guest saveGuest(Display displayEdition, @Validated GuestForm form) throws IOException {
		Long id = form.getId();
		Guest guest = (id != null) ? guestRepository.findById(id).orElseThrow() : new Guest();
		guest.setEdition(currentEditionService.findEdition(displayEdition));
		return saveGuest(form, guest);
	}

	public Guest saveGuest(@Validated GuestForm form, Guest guest) throws IOException {
		guest.setName(form.getName());
		guest.setDescription(form.getDescription());

		var pictureFile = form.getPictureFile();
		String folder = guest.getEdition().getName() + "/invites";
		if (!pictureFile.isEmpty()) {
			var tmp = pictureFile.getOriginalFilename().split("[.]");
			String fileExtension = (tmp.length > 0) ? "." + tmp[tmp.length - 1] : "";
			var url = uploadService.uploadFile(folder, "guest_" + System.currentTimeMillis() + fileExtension,
					pictureFile);
			guest.setImageUrl(url);
		}
		return guestRepository.save(guest);
	}

	public void deleteGuest(Long id) {
		// TODO remove image
		guestRepository.deleteById(id);
	}
}
