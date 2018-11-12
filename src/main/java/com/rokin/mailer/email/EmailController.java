package com.rokin.mailer.email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) throws IOException {
		EmailResponseDto emailResponseDto = this.emailService.sendEmail(emailDto);

		return ResponseEntity.status(HttpStatus.valueOf(emailResponseDto.getStatus()))
				.body(emailResponseDto.getMessage());
	}
}
