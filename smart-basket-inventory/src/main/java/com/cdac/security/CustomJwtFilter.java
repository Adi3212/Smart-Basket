package com.cdac.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ Step 0: Exclude public paths
        String path = request.getRequestURI();
        List<String> excludedPaths = List.of(
            "/grocery/expiry-soon",
            "/users/signin",
            "/users/signup"
        );

        if (excludedPaths.contains(path)) {
            log.info("Skipping JWT auth for public path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Step 1: Extract and validate JWT
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            String jwt = headerValue.substring(7);
            log.info("JWT in request header: {}", jwt);

            try {
                Authentication authentication = jwtUtils.populateAuthenticationTokenFromJWT(jwt);
                if (authentication != null && authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("JWT auth successful for user: {}", authentication.getName());
                }
            } catch (Exception ex) {
                log.warn("Invalid JWT: {}", ex.getMessage());
                // Optional: set 401 status or let it continue unauthenticated
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // return;
            }
        }

        // ✅ Step 2: Continue with filter chain
        filterChain.doFilter(request, response);
    }
}
