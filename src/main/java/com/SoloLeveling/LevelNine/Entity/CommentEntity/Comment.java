package com.SoloLeveling.LevelNine.Entity.CommentEntity;

import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Entity.BaseEntity.BaseEntity;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity {
    
    @Column(name = "comment")
    private String comment;

    @Column(name="depth")
    private Integer depth;

    @Column(name="isDeleted")
    private boolean isDeleted=false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "article_id")// what it does join the table  through forgin key
    private Artical article;


    @ManyToOne
    @JoinColumn(name = "parentId")
    private Comment parentComment;


    @OneToMany(mappedBy = "parentComment")
    private List<Comment>replies;
}
