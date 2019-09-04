package com.p.pichincha.mbbk.authentication.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.p.pichincha.mbbk.authentication.dto.DataLoginDTO;
import com.p.pichincha.mbbk.authentication.dto.DataSettingDTO;
import com.p.pichincha.mbbk.authentication.dto.LoginRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.LoginResponseDTO;
import com.p.pichincha.mbbk.authentication.dto.PersonResponseDTO;
import com.p.pichincha.mbbk.authentication.dto.SettingResponseDTO;
import com.p.pichincha.mbbk.authentication.dto.UserDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateRequestDTO;
import com.p.pichincha.mbbk.authentication.dto.ValidateResponseDTO;
import com.p.pichincha.mbbk.authentication.model.Session;
import com.p.pichincha.mbbk.authentication.service.ISessionService;
import com.p.pichincha.mbbk.authentication.util.TokenUtil;
import com.p.pichincha.mbbk.authentication.util.UidUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/channel/mbbk/v1.0/auth/session")
public class LoginController extends ParentController{

	@Value("${security.signing-key}")
	private String signingKey;
	
	@Value("${security.url-api-person}")
	private String urlApiPerson;
	
	@Value("${security.url-api-setting}")
	private String urlApiSetting;
	
//	@Autowired
//	private IPersonService personService;
	
	@Autowired
	private ISessionService sessionService;
	
	@PostMapping(value = "/login", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public LoginResponseDTO logueo(@RequestBody LoginRequestDTO requestDTO) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		String tokenJwt= "";

        try {
			LocalDateTime dateStart =  LocalDateTime.now();
			LocalDateTime dateExpiration = dateStart.plusSeconds(TokenUtil.TIME_EXPIRATION_SECONDS);
			String uid = UidUtil.encodeUid(requestDTO.getIbsCode(), dateStart);
	
			//
			RestTemplate restTemplatePerson = new RestTemplate();
			HttpHeaders headers = this.basicHeaders();
	        
	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	        
            ResponseEntity<PersonResponseDTO> responsePersonEntity = restTemplatePerson.exchange(String.format(urlApiPerson, uid), HttpMethod.GET, entity, PersonResponseDTO.class);
            PersonResponseDTO personDTO = responsePersonEntity.getBody();
			
			if (personDTO!=null && personDTO.getData().getName() != null) {
	            log.info(personDTO.toString());
				
				TokenUtil tokenUtil = new TokenUtil();
				tokenJwt = tokenUtil.getJWTToken(signingKey, requestDTO.getIbsCode(), dateStart, dateExpiration);
				
				Session sesion = new Session();
				sesion.setIbs(requestDTO.getIbsCode());
				sesion.setToken(tokenJwt);
				sesion.setUid(uid);
				sesion.setExpTime(dateExpiration);
				sessionService.registrar(sesion);
	
//				SettingResponseDTO settingResponseDTO = new SettingResponseDTO();
				
	//			RestTemplate restTemplate = new RestTemplate();
				
		        RestTemplate restTemplateSetting = new RestTemplate();
		        
		        ResponseEntity<SettingResponseDTO> responseSettingEntity = restTemplateSetting.exchange(String.format(urlApiSetting, uid), HttpMethod.GET, entity, SettingResponseDTO.class);
		        SettingResponseDTO settingResponseDTO = responseSettingEntity.getBody();
	            log.info(settingResponseDTO.toString());
		        //
	//	        settingDTO = restTemplateSetting.getForObject(String.format(urlApiSetting, uid), SettingDTO.class);
				
		        DataSettingDTO dataSettingDTO = new DataSettingDTO(settingResponseDTO.getData().isShowAmount());
				
				UserDTO userDTO = new UserDTO(personDTO.getData().getName(), personDTO.getData().getLastName(), uid);
				
				DataLoginDTO dataDTO = new DataLoginDTO(tokenJwt, userDTO, dataSettingDTO);
				
				loginResponseDTO.setCode("000000");
				loginResponseDTO.setDescription("Se realizó correctamente");
				loginResponseDTO.setData(dataDTO);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return loginResponseDTO;
	}
	
	@PostMapping(value = "/validate", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ValidateResponseDTO validar(@RequestHeader("Authorization") String token,
									   @RequestBody ValidateRequestDTO requestDTO) {
		ValidateResponseDTO validateResponseDTO = new ValidateResponseDTO();
		try {
			validateResponseDTO = sessionService.validarTokenUid(token, requestDTO);
			validateResponseDTO.setCode("000000");
			validateResponseDTO.setDescription("Se realizó correctamente");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return validateResponseDTO;
	}
}
