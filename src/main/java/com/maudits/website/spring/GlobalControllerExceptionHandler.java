package com.maudits.website.spring;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {
	private final MauditService mauditService;

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String showMissingPage(Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));
		return "error/404";
	}
}
