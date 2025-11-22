package com.SoloLeveling.LevelNine.Entity.UserEntity;
import com.SoloLeveling.LevelNine.Entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {


  @Column(name = "name")
  private String username;


    @Column(name = "password")
    private String password;


    
}
