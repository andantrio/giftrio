package com.fluffytrio.giftrio.security.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        String msg = "로그인 실패";

        if (authenticationException instanceof DisabledException) {
            msg += " - 비활성화된 계정입니다.";
        } else if (authenticationException instanceof CredentialsExpiredException) {
            msg += " - 만료된 로그인 정보입니다.";
        } else if (authenticationException instanceof BadCredentialsException) {
            msg += " - 로그인 정보가 유효하지 않습니다.";
        }

        setDefaultFailureUrl("/login?error=true&exception="+msg);
        super.onAuthenticationFailure(request, response, authenticationException);
    }
}
