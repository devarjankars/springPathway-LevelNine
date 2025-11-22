package com.SoloLeveling.LevelNine.Repository.CommentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SoloLeveling.LevelNine.Entity.CommentEntity.Comment;
public interface  CommentRepository extends JpaRepository<Comment, Long> {
    
}
