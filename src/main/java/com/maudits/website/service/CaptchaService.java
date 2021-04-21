package com.maudits.website.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maudits.website.domain.CaptchaCheckResponse;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CaptchaService {
	@Value("${recaptcha.secret}")
	private String captchaSecret;

	public boolean checkResponse(String reCaptchaResponse) {
		// TODO Use same json serializer as rest of website

		Gson gson = new GsonBuilder().create();

		try {
			HttpResponse<String> response = Unirest.post("https://www.google.com/recaptcha/api/siteverify")
					.header("content-type", "application/json").queryString("secret", captchaSecret)
					.queryString("response", reCaptchaResponse).asString();

//			JsonFactory jsonF = new JsonFactory();
//			JsonParser jp = jsonF.createParser(response.getBody());
//			CaptchaCheckResponse captchaCheckResponse = read(jp);

			CaptchaCheckResponse captchaCheckResponse = gson.fromJson(response.getBody(), CaptchaCheckResponse.class);
			List<String> errorCodes = captchaCheckResponse.getErrorCodes();
			if (errorCodes != null && !errorCodes.isEmpty()) {
				log.info("Captcha verification failed with error codes :"
						+ errorCodes.stream().collect(Collectors.joining()));
				return false;
			}
			return captchaCheckResponse.isSuccess();
		} catch (UnirestException e) {
			e.printStackTrace();
			return false;
		}
	}

}
