package com.fluffytrio.giftrio.utils.mail;

import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/login/emailConfirm")
    public String mailConfirm(@RequestBody UserRequestDto user) throws MessagingException, UnsupportedEncodingException {
        String authCode = mailService.sendConfirmEmail(user.toEntity().getEmail());
        return authCode;
    }
}
