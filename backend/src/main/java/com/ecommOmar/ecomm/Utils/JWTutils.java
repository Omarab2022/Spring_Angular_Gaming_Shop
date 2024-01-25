package com.ecommOmar.ecomm.Utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component

public class JWTutils {


	public static final String SECRET = "1463106196117249186102860126ghk91618" +
			"60162401264012461024610264016401640612061064106416410641" ;

	public String generateToken(String userName){


		Map<String , Object> claims = new HashMap<>();

		return createToken(claims,userName);

	}

	private String createToken(Map<String, Object> claims, String userName) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+10000*60*30))
				.signWith(getSignKey() , SignatureAlgorithm.HS256).compact();

	}

	private Key getSignKey() {
		byte[] keybytes  = Decoders.BASE64.decode(SECRET);

		return Keys.hmacShaKeyFor(keybytes);
	}


	public String extractUsername( String token){

		return extractClaims(token , Claims::getSubject);

	}

	public <T> T extractClaims(String token, Function<Claims , T> claimsResolver) {

		final Claims claims  =extractAllClaims(token);

		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).
				build().parseClaimsJws(token).getBody();
	}


	private Boolean isTokenExpired(String token){

		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractClaims(token , Claims::getExpiration);
	}

	public Boolean validateToken(String token , UserDetails userDetails){
		final String username  = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
