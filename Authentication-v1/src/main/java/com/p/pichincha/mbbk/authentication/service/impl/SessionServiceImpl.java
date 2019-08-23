package com.p.pichincha.mbbk.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.pichincha.mbbk.authentication.dao.ISessionDAO;
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

}
