


package com.SoloLeveling.LevelNine.Repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   
   Optional<UserEntity> findByUsername(String username);
   
}
