package com.SoloLeveling.LevelNine.Controllers.CommentController;
import com.SoloLeveling.LevelNine.DTOs.CommentRequestDTO;
import com.SoloLeveling.LevelNine.DTOs.CommentResponseDTO;
import com.SoloLeveling.LevelNine.Service.CommentServices.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/v1/")

public class CommentController {
   private final CommentService commentService;

    public CommentController(  CommentService commentService){
        this.commentService=commentService;
    }


    @PostMapping("artical/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long articleId, @RequestBody CommentRequestDTO  commentRequestDTO ){
        try{
            CommentResponseDTO response= commentService.createComment(articleId,commentRequestDTO);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("artical/{articleId}/initalComment")
    public ResponseEntity<?> getLevelFirstComment( @PathVariable Long articleId,@RequestParam (defaultValue = "10") int page, @RequestParam (defaultValue = "10") int size){
        try{

        }catch
    }



}
