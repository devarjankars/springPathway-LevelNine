package com.SoloLeveling.LevelNine.Entity.FollowEntity;

import com.SoloLeveling.LevelNine.Entity.BaseEntity.BaseEntity;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;




@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follows")
public class FollowEntity extends  BaseEntity {
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name="following_id")
    private UserEntity following;

}
