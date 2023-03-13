package com.dxc.mts.services;

import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.mts.dto.CredentialsDto;
import com.dxc.mts.dto.UserDto;
import com.dxc.mts.exceptions.AppException;
import com.dxc.mts.mappers.UserMapper;
import com.dxc.mts.model.User;
import com.dxc.mts.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public UserDto signIn(CredentialsDto credentialsDto) {
		User user = userRepository.findByEmailAddress(credentialsDto.getEmailAddress())
				.orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
			return userMapper.toUserDto(user, createToken(user));
		}
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UserDto validateToken(String token) {
		String login = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		Optional<User> userOptional = userRepository.findByEmailAddress(login);
		if (userOptional.isEmpty()) {
			throw new AppException("User not found", HttpStatus.NOT_FOUND);
		}
		User user = userOptional.get();
		return userMapper.toUserDto(user, createToken(user));
	}

	private String createToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getEmailAddress());
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000); // 1 hour
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
}
