package com.fluffytrio.giftrio.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluffytrio.giftrio.auth.dto.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();


    public EmailPasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                             AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher);
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        System.out.println("check: "+email+" "+password);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        return super.getAuthenticationManager().authenticate(authRequest);
    }
}
