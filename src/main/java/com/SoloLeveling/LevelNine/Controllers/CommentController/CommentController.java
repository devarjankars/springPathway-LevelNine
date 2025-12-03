package com.SoloLeveling.LevelNine.Controllers.CommentController;
import com.SoloLeveling.LevelNine.DTOs.CommentRequestDTO;
import com.SoloLeveling.LevelNine.DTOs.CommentResponseDTO;
import com.SoloLeveling.LevelNine.Service.CommentServices.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/v1")

public class CommentController {
   private final CommentService commentService;

    public CommentController(  CommentService commentService){
        this.commentService=commentService;
    }


    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long articleId, @RequestBody CommentRequestDTO  commentRequestDTO ){
        try{
            CommentResponseDTO response= commentService.createComment(articleId,commentRequestDTO);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/articles/{articleId}/comment")
    public ResponseEntity<?> getLevelFirstComment( @PathVariable Long articleId,@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size){
        try{

            Page<CommentResponseDTO> response = commentService.getInitalComment(articleId,  page , size);
            return ResponseEntity.status(200).body(response);
        }
        catch(NoSuchElementException e){
           return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }
    @GetMapping("/comment/{parentId}/replies")
    public ResponseEntity<?> getReplies( @PathVariable Long parentId){
        try{

            List<CommentResponseDTO> response = commentService.getReplies(parentId);
            return ResponseEntity.status(200).body(response);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> updateComment( @PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO ){
        try{

            CommentResponseDTO response= commentService.updatedCommentById(commentId, commentRequestDTO);
            return ResponseEntity.status(200).body(response);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> removeComment( @PathVariable Long commentId){
        try{

            CommentResponseDTO response= commentService.removeComment(commentId);
            return ResponseEntity.status(200).body(response);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }





}
