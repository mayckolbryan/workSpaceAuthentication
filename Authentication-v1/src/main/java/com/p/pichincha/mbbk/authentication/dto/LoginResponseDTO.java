package com.p.pichincha.mbbk.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO extends ResponseDTO{

	private DataLoginDTO data;
}