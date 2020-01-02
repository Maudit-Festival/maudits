package com.maudits.website.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaCheckResponse {
	private boolean success;
	@SerializedName("challenge_ts")
	private String challengeTs;
	private String hostname;
	@SerializedName("error-codes")
	private List<String> errorCodes;
}