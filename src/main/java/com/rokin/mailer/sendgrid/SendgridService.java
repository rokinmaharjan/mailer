package com.rokin.mailer.sendgrid;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rokin.mailer.email.EmailDto;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class SendgridService {

	@Value("${sendgrid.api-key}")
	private String sendgridApiKey;

	public static final Logger logger = LoggerFactory.getLogger(SendgridService.class);

	private SendGrid getSendgrid() {
		return new SendGrid(sendgridApiKey);
	}

	public Response sendEmail(EmailDto emailDto) throws IOException {
		Email sender = new Email(emailDto.getFrom());
		Email recipient = new Email(emailDto.getTo());
		String subject = emailDto.getSubject();
		Content content = new Content("text/html", emailDto.getBody());

		// Personalization personalization = new Personalization();
		// personalization.addCc(new Email("rokinfuse@gmail.com"));
		// personalization.addTo(recipient);
		// personalization.addBcc(new Email("fusedrokin@gmail.com"));
		// personalization.addCustomArg("subject", "HAHAHAH");
		// personalization.setSendAt(1495333950);

		Mail mail = new Mail(sender, subject, recipient, content);

		// mail.addPersonalization(personalization);
		// mail.setFrom(sender);
		// mail.setSubject("HAHAH");
		// mail.addContent(content);
		// mail.setReplyTo(new Email("invalidgranttest@gmail.com"));

		SendGrid sendgrid = getSendgrid();
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sendgrid.api(request);
			return response;
		} catch (IOException ex) {
			logger.info("Exception occured while sending email to {}. Exception : {}", emailDto.getTo(),
					ex.getMessage());
			throw ex;
		}
	}

}
