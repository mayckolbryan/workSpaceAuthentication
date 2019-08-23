package com.p.pichincha.mbbk.authentication.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.p.pichincha.mbbk.authentication.dto.DataLoginDTO;
import com.p.pichincha.mbbk.authentication.dto.DataSettingDTO;
import com.p.pichincha.mbbk.authentication.dto.LoginRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.LoginResponseDTO;
import com.p.pichincha.mbbk.authentication.dto.PersonDTO;
import com.p.pichincha.mbbk.authentication.dto.SettingDTO;
import com.p.pichincha.mbbk.authentication.dto.UserDTO;
import com.p.pichincha.mbbk.authentication.model.Session;
import com.p.pichincha.mbbk.authentication.service.IPersonService;
import com.p.pichincha.mbbk.authentication.service.ISessionService;
import com.p.pichincha.mbbk.authentication.util.TokenUtil;

@RestController
@RequestMapping("/channel/mbbk/v1.0/auth/session")
public class LoginController {

	@Value("${security.signing-key}")
	private String signingKey;
	
	@Value("${security.url-api-person}")
	private String urlApiPerson;
	
	@Value("${security.url-api-person-end}")
	private String urlApiPersonEnd;
	
	@Value("${security.url-api-setting}")
	private String urlApiSetting;
	
	@Value("${security.url-api-setting-end}")
	private String urlApiSettingEnd;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private ISessionService sessionService;
	
	@PostMapping(value = "/login", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public LoginResponseDTO logueo(@RequestBody LoginRequestDTO requestDTO) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		String tokenJwt= "";
		
		RestTemplate restTemplatePerson = new RestTemplate();
        
		PersonDTO personDTO = restTemplatePerson.getForObject(urlApiPerson + requestDTO.IBSCode + urlApiPersonEnd, PersonDTO.class);
//		Person per = new Person();		
//		per = personService.leerPorIbs(requestDTO.getIBSCode());
		
		if (personDTO!=null && personDTO.getData().getName() != null) {
			LocalDateTime dateStart =  LocalDateTime.now();
			LocalDateTime dateExpiration = dateStart.plusSeconds(TokenUtil.TIME_EXPIRATION_SECONDS);
			String uid = TokenUtil.getUid(requestDTO.IBSCode, dateStart);
			
			TokenUtil tokenUtil = new TokenUtil();
			tokenJwt = tokenUtil.getJWTToken(signingKey, requestDTO.IBSCode, dateStart, dateExpiration);
			
			Session sesion = new Session();
			sesion.setIbs(requestDTO.IBSCode);
			sesion.setToken(tokenJwt);
			sesion.setUid(uid);
			sesion.setExpTime(dateExpiration);
			sessionService.registrar(sesion);
			

			SettingDTO settingDTO = new SettingDTO();
	        RestTemplate restTemplateSetting = new RestTemplate();
	        settingDTO = restTemplateSetting.getForObject(urlApiSetting + requestDTO.IBSCode + urlApiSettingEnd, SettingDTO.class);
			
	        DataSettingDTO settingResponse = new DataSettingDTO(settingDTO.getData().isShowAmount());
			
			UserDTO userDTO = new UserDTO(personDTO.getData().getName(), personDTO.getData().getLastName(), uid);
			
			DataLoginDTO dataDTO = new DataLoginDTO(tokenJwt, userDTO, settingResponse);
			
			loginResponseDTO.setCode("000000");
			loginResponseDTO.setDescription("Se realizó correctamente");
			loginResponseDTO.setData(dataDTO);
		}
		return loginResponseDTO;
	}
}
