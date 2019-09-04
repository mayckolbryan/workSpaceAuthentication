package com.p.pichincha.mbbk.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SettingResponseDTO extends ResponseDTO{

	@JsonProperty("data")
	private DataSettingDTO data;
}
