package com.SoloLeveling.LevelNine.Security;


import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Repository.UserRepository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class UserAuthservices implements UserDetailsService {
       private final UserRepository userRepository;


    public UserAuthservices(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("UserDetails not found! Please check user info"+username);
        }
        return  new User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }


}
