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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CommentResponseDTO updatedCommentById( Long cmtId, CommentRequestDTO commentRequestDTO){
        //verification of data
       String currentLoggedUser= SecurityContextHolder.getContext().getAuthentication().getName();
       UserEntity  user= userRepository.findByUsername(currentLoggedUser)
            .orElseThrow( ()->  new IllegalStateException("Your Not Logged In Please Logged In!"));
       Comment existingComment= commentRepository.findById(cmtId)
              .orElseThrow(()-> new NoSuchElementException("Your orignal Comment is Already Deleted "));
        if( !user.getId().equals(existingComment.getUser().getId())){
           throw  new IllegalStateException("your Not Authorized User");
        }
        existingComment.setComment(commentRequestDTO.getContent());
        Comment savedComment= commentRepository.save(existingComment);

        CommentResponseDTO response = new CommentResponseDTO();
        response.setAuthorId(user.getId());
        response.setParentCommentId(
                savedComment.getParentComment() !=null? savedComment.getParentComment().getId():null
        );
        response.setId(savedComment.getId());
        response.setDepth(savedComment.getDepth());
        response.setUpdatedAt(savedComment.getUpdatedAt());
        response.setCreatedAt(savedComment.getCreatedAt());
        response.setContent(savedComment.getComment());
        response.setReplyCount(commentRepository. countByParentCommentAndIsDeletedFalse(savedComment));
        response.setAuthorName(user.getUsername());
        response.setIsDeleted(false);
        response.setCanRestore(false);
        return response;
    }

    public Page<CommentResponseDTO> getInitalComment (Long articalId , int page, int size) {
      Artical artical = articalRepository.findById(articalId)
              .orElseThrow(()-> new NoSuchElementException("Artical not found!")) ;
        Pageable pageable= PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<Comment> initalComments= commentRepository.findByArticleAndParentCommentIsNullAndIsDeletedFalse(artical, pageable);
        return initalComments.map((comment)->{
            CommentResponseDTO response = new CommentResponseDTO();
            response.setId(comment.getId());
            response.setDepth(comment.getDepth());
            response.setUpdatedAt(comment.getUpdatedAt());
            response.setCreatedAt(comment.getCreatedAt());
            response.setContent(comment.getComment());
            response.setReplyCount(commentRepository. countByParentCommentAndIsDeletedFalse(comment));
            response.setAuthorName(comment.getUser().getUsername());
            response.setAuthorId(comment.getUser().getId());
            response.setIsDeleted(comment.isDeleted());
            response.setParentCommentId(null);
            response.setCanRestore(false);
            return response;
        });



    }



}
