package com.p.pichincha.mbbk.authentication.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class TokenUtil {
	
//	@Value("${security.signing}")
//	String signingKey;
	
	public static final Long TIME_EXPIRATION_SECONDS = 1800L;

	public String getJWTToken(String signingKey, String ibs, LocalDateTime dateStart, LocalDateTime dateExpiration) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(ibs)
				.claim("authorities",
						grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(Timestamp.valueOf(dateStart))
				.setExpiration(Timestamp.valueOf(dateExpiration))
				.signWith(SignatureAlgorithm.HS512,
						signingKey.getBytes()).compact();
		
		return "Bearer " + token;
	}
}
