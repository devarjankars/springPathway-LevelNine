package com.SoloLeveling.LevelNine.Service.UserServices;

import org.springframework.stereotype.Service;

import com.SoloLeveling.LevelNine.Repository.UserRepository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.SoloLeveling.LevelNine.DTOs.userRequestDto;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Security.jwtUtil;

import java.util.Optional;


@Service
public class UserService {
    
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final jwtUtil  JwtUtil;
    public UserService( UserRepository userRepository, PasswordEncoder passwordEncoder, jwtUtil JwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.JwtUtil=JwtUtil;
    }


    // start here
      
    // Register services
    public void RegisterUser(userRequestDto userRegisterDto){
        try {
            // get the userbody and varify if it is valid
            String username = userRegisterDto.getUsername();
            String password = userRegisterDto.getPassword();
            //  hasing password
            String hashPassword = passwordEncoder.encode(password);
            // creating new object to store in db
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(hashPassword);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Login Service
    public String LoginUser(userRequestDto userloginDto){
        try {
            String username = userloginDto.getUsername();
            String password = userloginDto.getPassword();
           UserEntity user = userRepository.findByUsername(username).orElse(null);
            if (user == null) {
                return null;
            }
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            if(isPasswordMatch){
               String token= JwtUtil.createAuthToken(username);
               return token;
            }
            else {
                return "Please check your password";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean removeUserById( Long userId){

        try {

            UserEntity user = userRepository.findById(userId).orElse(null);

            if (user != null) {
                userRepository.delete(user);
                return !userRepository.existsById(userId);
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }
    }
    public UserEntity updateUserById( long userId , userRequestDto userRequestdto){
        try{
            UserEntity user= userRepository.findById(userId).orElse(null);
            if(user==null){
                return null;
            }
            user.setUsername(userRequestdto.getUsername());
            if(userRequestdto.getPassword()!=null){
                String HashPassword= passwordEncoder.encode(userRequestdto.getPassword());
                user.setPassword(HashPassword);
            }
            userRepository.save(user);
            return user;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }











}