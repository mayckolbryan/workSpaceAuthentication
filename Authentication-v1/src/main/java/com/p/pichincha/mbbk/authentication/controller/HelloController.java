package com.p.pichincha.mbbk.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p.pichincha.mbbk.authentication.util.UidUtil;

@RestController
@RequestMapping("hello")
public class HelloController {

	@GetMapping("/{uid}")
	public String helloWorld(@PathVariable("uid") String uid) {
//		return "Hello "+name+"!!";
		return UidUtil.decodeUid(uid);
	}
}
