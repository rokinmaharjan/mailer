package com.rokin.mailer.email;

public class EmailDto {
	private String to;
	private String from;
	private String subject;
	private String body;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "EmailDto [to=" + to + ", from=" + from + ", subject=" + subject + ", body=" + body + "]";
	}

}
