package com.maudits.website.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactMessageForm {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String messageContent;
}
