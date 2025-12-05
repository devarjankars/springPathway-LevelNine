package com.SoloLeveling.LevelNine.Repository.LikeRepoistory;

import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SoloLeveling.LevelNine.Entity.LikeEntity.LikeEntity;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long>{


    Optional<LikeEntity> findByUserAndArticle(UserEntity user, Artical artical);
    Integer countByArticle(Artical artical);
}
