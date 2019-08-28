package com.p.pichincha.mbbk.authentication.util;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UidUtil {
	public static Integer lengthHhMmSs = 6;
	public static Integer lengthYyMmDd = 6;

	public static String decodeUid(String uid) {
		Integer longitudUid = uid.length();
		String ibs = "";
		if (longitudUid > 0) {
			ibs = uid.substring(lengthHhMmSs, longitudUid - lengthYyMmDd);
		}
		
		return ibs;
	}
	
	public static String encodeUid(String ibs, LocalDateTime dateStart) {
		
		String uid = String.format("%02d", dateStart.getSecond()) + String.format("%02d", dateStart.getMinute())
					+ String.format("%02d", dateStart.getHour()) + ibs + String.valueOf(dateStart.getYear()).substring(2)
					+ String.format("%02d", dateStart.getMonthValue()) + String.format("%02d", dateStart.getDayOfMonth());
		
		return uid;
	}
}
