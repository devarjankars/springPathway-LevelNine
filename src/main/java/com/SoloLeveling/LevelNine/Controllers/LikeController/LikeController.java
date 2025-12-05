package com.SoloLeveling.LevelNine.Controllers.LikeController;


import com.SoloLeveling.LevelNine.DTOs.LikeResponseDTO;
import com.SoloLeveling.LevelNine.Service.LikeService.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/articles/{articleId}/like")
    public ResponseEntity<?> toggleLikeBtnApi(@PathVariable Long articleId) {
        try {
            LikeResponseDTO likeResponseDTO = likeService.toggleRequest(articleId);
             return ResponseEntity.ok(likeResponseDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/articles/{articleId}/likes")
    public ResponseEntity<?> getLikes(@PathVariable Long articleId) {
        try {
            LikeResponseDTO likeResponseDTO = likeService.getLikedCount(articleId);
              return ResponseEntity.ok(likeResponseDTO);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
