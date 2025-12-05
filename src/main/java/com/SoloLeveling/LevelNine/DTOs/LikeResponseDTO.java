package com.SoloLeveling.LevelNine.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {

    private Boolean Liked;
    private Integer totalLikeCount;
}
