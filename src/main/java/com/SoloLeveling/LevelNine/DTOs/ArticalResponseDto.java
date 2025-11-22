package com.SoloLeveling.LevelNine.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticalResponseDto {
    
      private String title;
      private String content;
      private String authorName;
      private Integer likesCount;
      private Integer commentsCount;
      private Long userId;
      private Long articalId;


}
