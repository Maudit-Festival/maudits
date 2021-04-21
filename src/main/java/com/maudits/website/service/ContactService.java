package com.maudits.website.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.maudits.website.domain.form.ContactMessageForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {
	private final JavaMailSender mailSender;

	public boolean sendEmail(ContactMessageForm form) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("contact.maudits@gmail.com");
		message.setTo("terreur.nocturne.asso@gmail.com");
		message.setSubject("[Formulaire de contact mauditfestival.com] Un nouveau message de " + form.getFirstName()
				+ " " + form.getLastName() + " ( " + form.getEmailAddress() + ")");
		message.setReplyTo(form.getEmailAddress());
		message.setText(form.getMessageContent());
		try {
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			log.warn("Error while trying to send contact email", e);
			return false;
		}
	}

}
