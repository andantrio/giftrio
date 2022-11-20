package com.fluffytrio.giftrio.utils.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    private final String MAIL_FORM = "giftrio.contact@gmail.com";
    private String authCode;

    public String sendConfirmEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        MimeMessage emailForm = createEmailConfirmForm(toEmail);
        emailSender.send(emailForm);

        return authCode;
    }

    public MimeMessage createEmailConfirmForm(String email) throws MessagingException, UnsupportedEncodingException {
        createCode();
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("회원가입 인증 번호");
        message.setFrom("giftrio.contact@gmail.com");
        message.setText(setContext(authCode, "mail/confirm"), "utf-8", "html");

        return message;
    }

    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authCode = key.toString();
    }

    private String setContext(String code, String location) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(location, context);
    }
}
