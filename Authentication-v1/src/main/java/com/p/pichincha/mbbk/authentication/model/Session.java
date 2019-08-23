package com.p.pichincha.mbbk.authentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
@Entity
@Table(name = "session")
public class Session {

	@Id
	@Column(name = "token", nullable = false, length = 500)
	private String token;
	
	@Column(name = "uid", nullable = false, length = 80)
	private String uid;
	
	@Column(name = "ibs", nullable = false, length = 9)
	private String ibs;
	
	@Column(name = "expTime", nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime expTime;
}
