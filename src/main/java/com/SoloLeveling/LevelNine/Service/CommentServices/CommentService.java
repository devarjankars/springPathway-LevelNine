package com.SoloLeveling.LevelNine.Service.CommentServices;
import com.SoloLeveling.LevelNine.Entity.CommentEntity.Comment;
import com.SoloLeveling.LevelNine.DTOs.CommentRequestDTO;
import com.SoloLeveling.LevelNine.DTOs.CommentResponseDTO;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Entity.CommentEntity.Comment;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Repository.ArticalRepository.ArticalRepository;
import com.SoloLeveling.LevelNine.Repository.CommentRepository.CommentRepository;
import com.SoloLeveling.LevelNine.Repository.UserRepository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.NoSuchElementException;

public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private  final ArticalRepository articalRepository;

    public CommentService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            ArticalRepository articalRepository
    ){
        this.commentRepository=commentRepository;
        this.userRepository=userRepository;
        this.articalRepository=articalRepository;

    }

    public CommentResponseDTO createComment ( Long articalId, CommentRequestDTO commentRequestDTO ){
        String currentLoggedUsername= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(currentLoggedUsername)
                .orElseThrow( ()-> new IllegalStateException( "You are not Logged In! Please logged in first"));
        Artical artical= articalRepository.findById(articalId)
                .orElseThrow(()-> new NoSuchElementException("Artical Not Found! Unable to create comment on Not Found Entity"));
        Comment parentComment = commentRepository.findById(commentRequestDTO.getParentCommentId()).orElse(null);
           if( parentComment!=null &&!parentComment.getArticle().getId().equals(articalId)){
           throw new IllegalStateException("No Parent Comment FOund to Add your Reply!");
           }
           if(parentComment !=null && parentComment.getDepth() >= 5) {
               throw new IllegalStateException("We are Reached the nested Comment Limit!");
           }

         Comment  comment= new Comment();
        comment.setComment(commentRequestDTO.getContent());
        comment.setParentComment(parentComment);
        comment.setUser(user);

         if (parentComment==null){
             comment.setDepth(0);
         }
         else{
             comment.setDepth(parentComment.getDepth()+1);
         }
        comment.setArticle(artical);

         Comment SavedComment= commentRepository.save(comment);

         CommentResponseDTO commentResponseDTO= new CommentResponseDTO();
         commentResponseDTO.setCreatedAt(SavedComment.getCreatedAt());
         commentResponseDTO.setUpdatedAt(SavedComment.getUpdatedAt());
         commentResponseDTO.setContent(SavedComment.getComment());
         commentResponseDTO.setId(SavedComment.getId());
         commentResponseDTO.setDepth(SavedComment.getDepth());
         commentResponseDTO.setAuthorName(user.getUsername());
         commentResponseDTO.setIsDeleted(SavedComment.isDeleted());
         commentResponseDTO.setCanRestore(!SavedComment.isDeleted());
         commentResponseDTO.setReplyCount(0);
         commentResponseDTO.setAuthorId(user.getId());
         commentResponseDTO.setParentCommentId(parentComment!=null? parentComment.getId() : null);
         return commentResponseDTO;


    }
}
