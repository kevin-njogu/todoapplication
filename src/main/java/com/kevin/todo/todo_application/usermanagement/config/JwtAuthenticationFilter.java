package com.kevin.todo.todo_application.usermanagement.config;

import com.kevin.todo.todo_application.usermanagement.service.JwtService;
import com.kevin.todo.todo_application.usermanagement.service.UserDetailsServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceimpl;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final String jwtToken;
       final String userEmail;
        //1. Get the Authorization header
        final String authHeader = request.getHeader("Authorization");
        //2.check if the authorization header exists
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        //3.Extract token from the Authorization header from index 7(end of bearer)
        jwtToken = authHeader.substring(7);
        //4.Extract userEmail from the JWT token
        userEmail = jwtService.extractUsername(jwtToken);
        //5. Check if the userEmail exists and that the user is not already authenticated. If te authentication object is null it means the user is not already authenticated
        if(Objects.nonNull(userEmail) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(userEmail);
            //Check id the token is valid
            if(jwtService.isTokenValid(jwtToken, userDetails)) {
                //update the security context and send the request to dispatcher servlet
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
