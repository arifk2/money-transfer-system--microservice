package com.dxc.mts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.mts.dto.BaseResponse;
import com.dxc.mts.dto.CredentialsDto;
import com.dxc.mts.dto.UserDto;
import com.dxc.mts.services.EventLogService;
import com.dxc.mts.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserControllers {

	private final UserService userService;

	private final MessageSource source;

	@Autowired
	private EventLogService eventLogService;

	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@RequestBody CredentialsDto credentialsDto) {
		log.info("Trying to login {}", credentialsDto.getEmailAddress());
		UserDto token = userService.signIn(credentialsDto);
		if (token != null && token.getToken() != null) {
			eventLogService.saveLoginEvent(token.getEmailAddress());
		}
		return new ResponseEntity<Object>(
				new BaseResponse(HttpStatus.OK.value(), source.getMessage("mts.success.message", null, null), token),
				HttpStatus.OK);
	}

	@PostMapping("/validateToken")
	public ResponseEntity<Object> signIn(@RequestParam String token) {
		log.info("Trying to validate token {}", token);
		return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
				source.getMessage("mts.success.message", null, null), userService.validateToken(token)), HttpStatus.OK);
	}
}
