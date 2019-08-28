package com.p.pichincha.mbbk.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "setting")
@NoArgsConstructor
public class Setting {
	
	@Id
	@Column(name = "ibs", nullable = false, length = 9)
	private String ibs;

	@Column(name = "showAmount", nullable = false)
	private boolean showAmount;
}
