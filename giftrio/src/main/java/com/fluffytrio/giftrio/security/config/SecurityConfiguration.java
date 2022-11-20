package com.fluffytrio.giftrio.security.config;

import com.fluffytrio.giftrio.security.filter.EmailPasswordAuthenticationFilter;
import com.fluffytrio.giftrio.security.handler.AuthSuccessHandler;
import com.fluffytrio.giftrio.security.service.EmailPasswordUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
    @Autowired
    EmailPasswordUserDetailsService emailPasswordUserDetailsService;

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users").permitAll()
                //.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/logout").authenticated()
                .and()
                .addFilterAt(getEmailPasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public EmailPasswordAuthenticationFilter getEmailPasswordAuthenticationFilter() {
        EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter = new EmailPasswordAuthenticationFilter(getLoginPath(), getAuthManager());
        emailPasswordAuthenticationFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        return emailPasswordAuthenticationFilter;
    }

    @Bean
    public AntPathRequestMatcher getLoginPath() {
        return new AntPathRequestMatcher("/api/v1/login", "POST");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(emailPasswordUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return new ProviderManager(Arrays.asList(new AuthenticationProvider[] {daoAuthenticationProvider}));
    }
}
