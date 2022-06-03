package com.example.demo.user;



import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {


    @Autowired
    private jwtUtil jwtUtil;
    @Autowired
    private UserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader("Authorization");
        String token=null;
        String email = null;
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYW1AZ21haWwuY29tIiwiaWF0IjoxNjU0MDAxNDc4LCJleHAiOjE2NTQwMzc0Nzh9.lJlShBn1puQb3DO7FBOuPE0_81CiQK0AVP9Jnge9A08
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token=authorizationHeader.substring(7);
            email=jwtUtil.extractEmail(token);
        }
        if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails userDetails =   service.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
