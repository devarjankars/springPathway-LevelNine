package com.SoloLeveling.LevelNine.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.SoloLeveling.LevelNine.Security.jwtAuthFilter;


@Configuration   // here telling spring this security Gurd of this Software
//telling it conifuration file it will scan look for bean method inside it  and also enble security
@EnableWebSecurity
public class SecurityConfig {

    //wite security rule for your app


    private final jwtAuthFilter jwtAuthfilter;

     public SecurityConfig(jwtAuthFilter jwtAuthfilter){
         this.jwtAuthfilter=jwtAuthfilter;
     }


    @Bean //spring create ans mange the  object
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{


        http.cors(cors ->cors.disable())
         .csrf(csrf ->csrf.disable())
         .authorizeHttpRequests(auth ->auth
                 .requestMatchers( "/api/v1/auth/register", "/api/v1/auth/login","/api/v1/artical/**","/api/v1/artical/getAllPopularArticals").permitAll()
                 .anyRequest().authenticated()
         )
                 .sessionManagement(session->session
                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 ).addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

   @Bean 
   public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
   }


   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
          return config.getAuthenticationManager();
   }


}