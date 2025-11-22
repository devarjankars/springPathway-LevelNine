package com.SoloLeveling.LevelNine.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration   // here telling spring this security Gurd of this Software
//telling it conifuration file it will scan look for bean method inside it  and also enble security
@EnableWebSecurity
public class SecurityConfig {

    //wite security rule for your app

    @Bean //spring create ans mange the  object
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{



         http.cors(cors ->cors.disable())
         .csrf(csrf ->csrf.disable())
         .authorizeHttpRequests(auth ->auth 

         .anyRequest().permitAll());
         //CSRF = Cross-Site Request Forgery ( Attack where evil site tricks you into making unwanted requests)
        return http.build();
    }

   @Bean 
   public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
   }


}