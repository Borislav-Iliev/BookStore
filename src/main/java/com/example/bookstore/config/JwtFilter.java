package com.example.bookstore.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null) {
            String[] authorizationTokens = authorization.split(" ");

            if (authorizationTokens.length == 2 && "Bearer".equals(authorizationTokens[0])) {
                try {
                    SecurityContextHolder.getContext()
                            .setAuthentication(this.userAuthenticationProvider.validateToken(authorizationTokens[1]));
                } catch (RuntimeException ex) {
                    SecurityContextHolder.clearContext();
                    throw ex;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
