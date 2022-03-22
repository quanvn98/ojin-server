package com.product.ojinserver.configuration;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.product.ojinserver.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private final String jwtSecret = "Ib3wc5WAlJO_nBRC0M-ZA";
	private final String jwtIssuer = "ojin_server";

	public String generateAccessToken(User user) {
		return Jwts.builder().setSubject(String.format("%s,%s", user.getId(), user.getUsername())).setIssuer(jwtIssuer)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1week
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject().split(",")[0];
	}

	public String getUsername(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject().split(",")[1];
	}

	public Date getExpirationDate(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getExpiration();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("Invalid JWT signature - " + ex.getMessage());
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token - " + ex.getMessage());
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token - " + ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token - " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty - " + ex.getMessage());
		}
		return false;
	}

}
