package com.p.pichincha.mbbk.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResponseDTO extends ResponseDTO{

	@JsonProperty("data")
	private DataValidateDTO data;
}
