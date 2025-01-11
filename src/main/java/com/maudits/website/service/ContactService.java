package com.maudits.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
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
	@Value("${spring.mail.username}")
	private String contactEmail;

	private final JavaMailSender mailSender;

	public boolean sendEmail(ContactMessageForm form) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(contactEmail);
		message.setTo("terreur.nocturne.asso@gmail.com");
		message.setSubject(
				"[Formulaire de contact mauditfestival.com] De " + form.getFirstName() + " " + form.getLastName());
		message.setReplyTo(form.getEmailAddress());
		message.setText(form.getMessageContent());
		try {
			mailSender.send(message);
			return true;
		} catch (MailSendException e) {
			log.warn("Error while trying to send contact email {" + message + "}", e);
			return false;
		} catch (Exception e) {
			log.warn("Error while trying to send contact email", e);
			return false;
		}
	}

}
