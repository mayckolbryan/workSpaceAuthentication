package com.p.pichincha.mbbk.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateRequestDTO {

	@JsonProperty("uid")
	public String uid;
}
