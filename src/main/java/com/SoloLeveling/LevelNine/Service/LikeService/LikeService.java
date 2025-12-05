package com.SoloLeveling.LevelNine.Service.LikeService;

import com.SoloLeveling.LevelNine.DTOs.LikeRequestDTO;
import com.SoloLeveling.LevelNine.DTOs.LikeResponseDTO;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Entity.LikeEntity.LikeEntity;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Repository.ArticalRepository.ArticalRepository;
import com.SoloLeveling.LevelNine.Repository.LikeRepoistory.LikeRepository;
import com.SoloLeveling.LevelNine.Repository.UserRepository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class LikeService {

    private  final LikeRepository likeRepository;
    private  final UserRepository userRepository;
    private  final ArticalRepository articalRepository;


    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ArticalRepository articalRepository){
        this.likeRepository=likeRepository;
        this.userRepository=userRepository;
        this.articalRepository=articalRepository;
    }



//   toggle Likw btn
    public LikeResponseDTO toggleRequest(Long articleID){
        String currentUsername= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(currentUsername).
                orElseThrow(()->new IllegalStateException("Your Not Logged In Pls loggedIn"));
        Artical artical = articalRepository.findById(articleID)
                .orElseThrow(()-> new NoSuchElementException("Article Not found in database cannot perform the given action "));
         LikeEntity exitingLike= likeRepository.findByUserAndArticle(user,artical).orElse(null);
         LikeResponseDTO  responseDTO= new LikeResponseDTO();
        if(exitingLike !=null){
            likeRepository.delete(exitingLike);
             responseDTO.setTotalLikeCount(likeRepository.countByArticle(artical));
             responseDTO.setLiked(false);
             return responseDTO;
        }
        LikeEntity newLike= new LikeEntity();
        newLike.setArticle(artical);
        newLike.setUser(user);
        likeRepository.save(newLike);
        responseDTO.setLiked(true);
        responseDTO.setTotalLikeCount(likeRepository.countByArticle(artical));
        return responseDTO;
    }

    public LikeResponseDTO getLikedCount(Long articleId){

        String currentUsername= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(currentUsername)
                .orElseThrow(()-> new IllegalStateException("Your Not Logged In!"));
        Artical article= articalRepository.findById(articleId)
                .orElseThrow(()-> new NoSuchElementException("the Article is not found in database"));
         Integer LikeCount= likeRepository.countByArticle(article);
         LikeEntity likeEntity= likeRepository.findByUserAndArticle(user,article)
                 .orElseThrow(()-> new NoSuchElementException("not found in database"));
         LikeResponseDTO responseDTO= new LikeResponseDTO();
         responseDTO.setLiked(true);
         responseDTO.setTotalLikeCount(LikeCount);
         return responseDTO;
    }




}
