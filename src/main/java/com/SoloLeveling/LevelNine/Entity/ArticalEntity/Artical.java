package com.SoloLeveling.LevelNine.Entity.ArticalEntity;

import  com.SoloLeveling.LevelNine.Entity.BaseEntity.BaseEntity;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.SoloLeveling.LevelNine.Entity.CommentEntity.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="artical")
public class Artical extends  BaseEntity  {



    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity user;

    @OneToMany(mappedBy="article",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    List<Comment> comment= new ArrayList<>();


}
