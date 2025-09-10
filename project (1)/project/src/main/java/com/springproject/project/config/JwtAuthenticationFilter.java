package com.springproject.project.config;


import ch.qos.logback.core.util.StringUtil;
import com.springproject.project.entities.User;
import com.springproject.project.services.JWTService;
import com.springproject.project.services.UserServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public JwtAuthenticationFilter(JWTService jwtService) {

        this.jwtService = jwtService;
    }


    private final JWTService jwtService ;

    private UserServicesImpl userServices ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
          final  String authHeader = request.getHeader("Authorization");
          final  String jwt ;
          final  String useEmail ;

          if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader , "Barer")){
              filterChain.doFilter(request , response) ;
              return;
          }

          jwt = authHeader.substring(7);
          useEmail = jwtService.extractUserName(jwt);

         if(jwtService.isToken(jwt , userServices)){
             SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

             UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken{
             userDetails , null , UserDetails.getAuthortites();

             }
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             securityContext.setAuthentication(token);
             SecurityContextHolder.setContext(securityContext);
         }

         filterChain.doFilter(request , response);
    }
}
