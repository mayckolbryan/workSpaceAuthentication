package com.p.pichincha.mbbk.authentication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.pichincha.mbbk.authentication.model.Person;

public interface IPersonDAO extends JpaRepository<Person, String>{

	Person findByIbs(String ibs);
}
