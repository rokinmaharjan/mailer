package com.rokin.mailer.email;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rokin.mailer.sendgrid.SendgridService;
import com.sendgrid.Response;

@Service
public class EmailService {

	@Autowired
	private SendgridService sendgridService;

	private static final int ACCEPTED_STATUS_CODE = 202;

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public EmailResponseDto sendEmail(EmailDto emailDto) throws IOException {
		Response response = this.sendgridService.sendEmail(emailDto);
		
		int status = response.getStatusCode();
		String message = "";

		if (status == ACCEPTED_STATUS_CODE) {
			message = "Email sent to " + emailDto.getTo();
			logger.info("Email sent to " + emailDto.getTo() + " with message id "
					+ response.getHeaders().get("X-Message-Id"));
		} else {
			message = "Email not sent to " + emailDto.getTo();
			logger.info(("Email not sent to " + emailDto.getTo()));
		}

		return new EmailResponseDto(status, message);
	}

}
