package com.luca.moviereviews.core.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.luca.moviereviews.core.model.EmailTemplateName;
import com.luca.moviereviews.core.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	
	private final SpringTemplateEngine templateEngine;
	
	@Value("${myApp.smtp.mail}")
	private String smtpMail;
	
	@Async
	public void sendMail(String to,String username, EmailTemplateName emailTemplate,String confirmationUrl,String activationCode,String subject) throws MessagingException {
		
		String templateName;
		
		templateName = emailTemplate==null?"confirm-email": emailTemplate.name();
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());
		
		Map<String,Object> properties =new HashMap<>();
		properties.put("username", username);
		properties.put("confirmationUrl", confirmationUrl);
		properties.put("activation_code", activationCode);
		
		Context context=new Context();
		
		context.setVariables(properties);
		helper.setFrom(this.smtpMail);
		helper.setTo(to);
		helper.setSubject(subject);
		String template = templateEngine.process(templateName, context);
		
		helper.setText(template,true);
		
		mailSender.send(mimeMessage);
		
			
	}
	
	
}
