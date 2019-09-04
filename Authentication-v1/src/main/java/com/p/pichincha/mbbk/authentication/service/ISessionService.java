package com.p.pichincha.mbbk.authentication.service;

import com.p.pichincha.mbbk.authentication.dto.ValidateRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateResponseDTO;
import com.p.pichincha.mbbk.authentication.model.Session;

public interface ISessionService {

	Session registrar(Session sesion);
	
	ValidateResponseDTO validarTokenUid(String headerRequest, ValidateRequestDTO validateRequestDTO);
}
