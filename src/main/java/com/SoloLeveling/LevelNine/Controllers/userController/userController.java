package com.SoloLeveling.LevelNine.Controllers.userController;

import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SoloLeveling.LevelNine.DTOs.userRequestDto;
import com.SoloLeveling.LevelNine.Service.UserServices.UserService;





// spring take the valid request to this layer
@RestController
@RequestMapping("/api/v1/auth")
public class userController {
    
       //Final is set once never change 
        private final UserService userService;
         public userController(UserService userService) {
             this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody userRequestDto userRequestdto){
      System.out.println("User Request Dto: " + userRequestdto);
        userService.RegisterUser(userRequestdto);
        return ResponseEntity
                .status(201)
                .body("User Created successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody userRequestDto userRequestdto){
       //invoke the login repo and
      String LoginToken=userService.LoginUser(userRequestdto);
      if(LoginToken!=null ){
        return ResponseEntity.status(200).body("User Logged IN here is authToken: "+LoginToken);
      }
      return ResponseEntity
              .status(401)
              .body("Something went wrong! ");
    }
    @DeleteMapping("/removeUser/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable  Long userId){
             boolean isRemoved = userService.removeUserById(userId);
        System.out.println(isRemoved    );
        return ResponseEntity
                .status(201)
                .body("User Deleted ");
    }

    @PutMapping("/updatedUserInfo/{userId}")
    public ResponseEntity<?> updatedUserData(@PathVariable long userId, @RequestBody userRequestDto userRequestdto){
        UserEntity user= userService.updateUserById( userId, userRequestdto);
        if(user!=null){
            return ResponseEntity
                    .status(200)
                    .body("sucessfully updated user data");
        }
        return ResponseEntity.status(404).build();
    }
    
}
