package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(Email email) throws Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(email.getData());
		String html = "";
		if(email.getReason()== Email.Reason.registration){
			html = templateEngine.process("Welcome", context);
		}else if(email.getReason()== Email.Reason.meeting){
			html = templateEngine.process("MeetingInvite", context);
		}

		helper.setTo(email.getTo());
		helper.setText(html, true);
		helper.setSubject(email.getSubject());
		helper.setFrom(email.getFrom());
		helper.addInline("idCard", new FileSystemResource(email.getData().get("file").toString()), "image/png");
		emailSender.send(message);
	}
}
