package com.SoloLeveling.LevelNine.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderThreadLocalAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@Component
public class jwtAuthFilter  extends OncePerRequestFilter {

    private final jwtUtil Jwtutil;
    private final UserAuthservices userAuthServices;

    public jwtAuthFilter( jwtUtil Jwtutil, UserAuthservices userAuthServices){
        this.Jwtutil=Jwtutil;
        this.userAuthServices=userAuthServices;
    }
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain)
            throws ServletException , IOException{

        final String authHeader= request.getHeader("Authorization");
        String username=null;
        String authToken=null;
        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            authToken=authHeader.substring(7);
            username= Jwtutil.getUsername(authToken);
        }
        if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userAuthServices.loadUserByUsername(username);
            if(Jwtutil.validateToken(authToken,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken tokenAuth= new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
                );
                tokenAuth.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(tokenAuth);

            }
        }

        filterChain.doFilter(request,response);


    }

}
