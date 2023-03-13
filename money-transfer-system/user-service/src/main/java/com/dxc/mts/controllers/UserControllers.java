package com.dxc.mts.controllers;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.mts.dto.BaseResponse;
import com.dxc.mts.dto.CredentialsDto;
import com.dxc.mts.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserControllers {

	private final UserService userService;

	private final MessageSource source;

	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@RequestBody CredentialsDto credentialsDto) {
		log.info("Trying to login {}", credentialsDto.getEmailAddress());

		return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
				source.getMessage("mts.success.message", null, null), userService.signIn(credentialsDto)),
				HttpStatus.OK);
	}

	@PostMapping("/validateToken")
	public ResponseEntity<Object> signIn(@RequestParam String token) {
		log.info("Trying to validate token {}", token);
		return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
				source.getMessage("mts.success.message", null, null), userService.validateToken(token)), HttpStatus.OK);
	}
}
