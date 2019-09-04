package com.p.pichincha.mbbk.authentication.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ParentController {

	public HttpHeaders basicHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Request-ID", "550e8400-e29b-41d4-a716-446655440000");
		headers.set("request-date", "2019-08-08T10:20:30");
		headers.set("app-code", "AP");
		headers.set("caller-name", "xxxxxx");
		
		return headers;
	}
}
