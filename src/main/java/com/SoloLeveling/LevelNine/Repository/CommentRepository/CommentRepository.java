package com.SoloLeveling.LevelNine.Repository.CommentRepository;

import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SoloLeveling.LevelNine.Entity.CommentEntity.Comment;
public interface  CommentRepository extends JpaRepository<Comment, Long> {

    Integer countByParentCommentAndIsDeletedFalse(Comment parent);

    Page<Comment> findByArticleAndParentCommentIsNullAndIsDeletedFalse(Artical artical, Pageable pageable);
}
