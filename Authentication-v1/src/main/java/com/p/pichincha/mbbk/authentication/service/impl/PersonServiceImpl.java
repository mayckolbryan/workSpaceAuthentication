package com.p.pichincha.mbbk.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.pichincha.mbbk.authentication.dao.IPersonDAO;
import com.p.pichincha.mbbk.authentication.model.Person;
import com.p.pichincha.mbbk.authentication.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService{

	@Autowired
	private IPersonDAO personDao;
	
	@Override
	public Person leerPorIbs(String ibs) {
		return personDao.findByIbs(ibs);
	}

}
