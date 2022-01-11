package com.maudits.website.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.DisplayEdition;
import com.maudits.website.service.MauditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MauditErrorController implements ErrorController {
	private final MauditService mauditService;

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		model.addAttribute("page", mauditService.makePageDisplayer(DisplayEdition.CURRENT));

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			model.addAttribute("errorCode", statusCode);

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error/500";
			}
		}
		return "error/generic";
	}
}