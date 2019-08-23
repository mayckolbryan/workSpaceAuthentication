package com.p.pichincha.mbbk.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {

	@Id
	@Column(name = "ibs", nullable = false, length = 9)
	private String ibs;

	@Column(name = "name", nullable = false, length = 80)
	private String name;

	@Column(name = "lastName", nullable = false, length = 80)
	private String lastName;
	
	@Column(name = "tipo", nullable = false, length = 20)
	private String tipo;
}
