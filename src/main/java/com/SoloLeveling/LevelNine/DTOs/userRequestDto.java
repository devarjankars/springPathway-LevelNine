package com.SoloLeveling.LevelNine.DTOs;

public class userRequestDto {
     
  private String username;
  private String password;



  //All arug Constructor
  public userRequestDto(String username, String password){
    this.username = username;
    this.password = password;
  }

  //NoArugConstructor
   public userRequestDto(){}

//  getter's ans setter's

   public String getUsername(){
    return this.username;

   }
   public String getPassword(){
    return this.password;
   }
   public void setUsername(String username){
    this.username = username;
   }
   public void setPassword(String password){
    this.password = password;
   }
    
}
