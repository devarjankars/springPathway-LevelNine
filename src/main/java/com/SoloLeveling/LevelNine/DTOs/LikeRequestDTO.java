package com.SoloLeveling.LevelNine.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {
    private Long articleId;
    private Boolean Liked;
    private  Long userId;

}
