package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void sendEmail(Member member ,Types types ) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String text= build(member);
		helper.setTo(member.getEmail());
		helper.setText(text, true); // set to html
		helper.setSubject("Hi");

		sender.send(message);
	}

	@Override
	public void sendEmail(Meeting member, Types types) throws Exception {

	}

	private String build(Member member) {
		Context context = new Context();
		context.setVariable("firstName", member.getFirstName());
		context.setVariable("middleName", member.getMiddleName());
		context.setVariable("lastName", member.getLastName());
		return templateEngine.process("mailTemplate", context);
	}
	private String buildAttachment(Member member) {
		Context context = new Context();
		return templateEngine.process("mailTemplate", context);
	}
}
