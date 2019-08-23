package com.p.pichincha.mbbk.authentication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.pichincha.mbbk.authentication.model.Session;

public interface ISessionDAO extends JpaRepository<Session, String>{

}
