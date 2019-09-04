package com.p.pichincha.mbbk.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonResponseDTO extends ResponseDTO{

	@JsonProperty("data")
	private DataPersonDTO data;
}
