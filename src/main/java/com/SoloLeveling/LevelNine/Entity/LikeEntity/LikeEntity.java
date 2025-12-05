package com.SoloLeveling.LevelNine.Entity.LikeEntity;

import com.SoloLeveling.LevelNine.Entity.BaseEntity.BaseEntity;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","article_id"})})
public class LikeEntity extends BaseEntity  {

 @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Artical article;

}
