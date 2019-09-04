package com.p.pichincha.mbbk.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.pichincha.mbbk.authentication.dao.ISessionDAO;
import com.p.pichincha.mbbk.authentication.dto.DataValidateDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateHeaderRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateResponseDTO;
import com.p.pichincha.mbbk.authentication.model.Session;
import com.p.pichincha.mbbk.authentication.service.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService{

	@Autowired
	private ISessionDAO sessionDao;
	
	@Override
	public Session registrar(Session sesion) {
		return sessionDao.save(sesion);
	}

	@Override
	public ValidateResponseDTO validarTokenUid(String headerRequest, ValidateRequestDTO validateRequestDTO) {
		ValidateResponseDTO validateResponseDTO = new ValidateResponseDTO();
		Session session = sessionDao.findByToken(headerRequest);
		if (session != null && session.getToken() != null) {
			boolean validate = session.getUid().equals(validateRequestDTO.getUid());
			
			validateResponseDTO.setData(new DataValidateDTO());
			validateResponseDTO.getData().setValidate(validate);
		}
		
		return validateResponseDTO;
	}

}
