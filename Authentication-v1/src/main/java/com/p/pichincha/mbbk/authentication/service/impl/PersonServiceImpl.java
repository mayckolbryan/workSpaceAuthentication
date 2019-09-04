package com.p.pichincha.mbbk.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.pichincha.mbbk.authentication.dao.IPersonDAO;
import com.p.pichincha.mbbk.authentication.dto.DataPersonDTO;
import com.p.pichincha.mbbk.authentication.dto.PersonResponseDTO;
import com.p.pichincha.mbbk.authentication.model.Person;
import com.p.pichincha.mbbk.authentication.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService{

	@Autowired
	private IPersonDAO personDao;
	
	@Override
	public PersonResponseDTO leerPorIbs(String ibs) {
		Person person = personDao.findByIbs(ibs);
		PersonResponseDTO personDTO = new PersonResponseDTO();
		personDTO.setData(new DataPersonDTO());
		personDTO.getData().setName(person.getName());
		personDTO.getData().setLastName(person.getLastName());
		return personDTO;
	}

}
