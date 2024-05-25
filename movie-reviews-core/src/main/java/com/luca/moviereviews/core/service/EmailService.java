package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.EmailTemplateName;

import jakarta.mail.MessagingException;

public interface EmailService {
	
	public void sendMail(String to,String username, EmailTemplateName emailTemplate,String confirmationUrl,String activationCode,String subject) throws MessagingException;

}
