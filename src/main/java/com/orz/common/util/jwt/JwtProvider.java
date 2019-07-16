package com.orz.common.util.jwt;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

 	@Autowired
	@Qualifier("app")
	private PropertiesFactoryBean app;
    
    private long tokenValidMilisecond = 1000L * 60 * 60;
    
    public String createJwt(String id) throws IOException {
    	Date date = new Date();
    	
    	String secret = String.valueOf(app.getObject().get("orz.jwt.secret"));
    	
        return Jwts.builder()
                	.setId(id)
                	.setIssuedAt(date)
                	.setExpiration(new Date(date.getTime() + tokenValidMilisecond)) // 유효시간 설정
                	.signWith(SignatureAlgorithm.HS256, secret) 
                	.compact();
    }
    
    public boolean validateJwt(String jwt) {
    	return getClaims(jwt) != null;
    }
    
    private Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser().setSigningKey(String.valueOf(app.getObject().get("orz.jwt.secret"))).parseClaimsJws(jwt);
        } catch (Exception e) {
        	return null;
        }
    }
}
