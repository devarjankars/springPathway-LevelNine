package com.SoloLeveling.LevelNine.Repository.FollowRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoloLeveling.LevelNine.Entity.FollowEntity.FollowEntity;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
    
}
