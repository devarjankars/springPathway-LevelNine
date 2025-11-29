package com.SoloLeveling.LevelNine.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private String content;
    private  Long id;
    private  String authorName;
    private  Long authorId;
    private Integer replyCount;
    private Long parentCommentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private Integer depth;
    private Boolean  canRestore;




}
