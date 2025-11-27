package com.SoloLeveling.LevelNine.Service.ArticalService;
import com.SoloLeveling.LevelNine.DTOs.ArticalRequestDto;
import com.SoloLeveling.LevelNine.DTOs.ArticalResponseDto;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import com.SoloLeveling.LevelNine.Repository.ArticalRepository.ArticalRepository;
import com.SoloLeveling.LevelNine.Repository.UserRepository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticalServices {

      private final ArticalRepository articalRepository;
      private final UserRepository userRepository;

    public ArticalServices(ArticalRepository articalRepository, UserRepository userRepository) {
        this.articalRepository= articalRepository;
        this.userRepository=userRepository;
    }


    public ArticalResponseDto createArtical(ArticalRequestDto articalRequestDto){
             String username= SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user= userRepository.findByUsername(username)
                    .orElseThrow( ()-> new IllegalStateException("Please Log In"));
            Artical artical= new Artical();
            artical.setContent(articalRequestDto.getContent());
            artical.setTitle(articalRequestDto.getTitle());
            artical.setUser(user);
             Artical savedAtical =articalRepository.save(artical);
            ArticalResponseDto responseDto= new ArticalResponseDto();
            responseDto.setArticalId(savedAtical.getId());
            responseDto.setTitle(savedAtical.getTitle());
            responseDto.setContent(savedAtical.getContent());
            responseDto.setAuthorName(savedAtical.getUser().getUsername());
            return responseDto;

    }

    public ArticalResponseDto getArticalById( long articalId){
             Artical artical = articalRepository.findById(articalId)
                     .orElseThrow(()-> new EntityNotFoundException("Artical not found"));
             ArticalResponseDto articalResponseDto = new ArticalResponseDto();
             articalResponseDto.setArticalId(artical.getId());
             articalResponseDto.setTitle(artical.getTitle());
             articalResponseDto.setContent(artical.getContent());
           articalResponseDto.setAuthorName(artical.getUser().getUsername());
           return articalResponseDto;
    }
    public  boolean removeArticalById(long articalId){
        String usernameFromToken= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(usernameFromToken)
                .orElseThrow(()-> new IllegalStateException("Your not loggedIn"));
        Artical artical= articalRepository.findById(articalId)
                .orElseThrow(()-> new NoSuchElementException("No Such artical found in databse"));
        if( !artical.getUser().getId().equals(user.getId())){
            throw new IllegalStateException("your not authorized to delete this artical");
        }
        articalRepository.delete(artical);
         return !articalRepository.existsById(articalId);
    }

    //Updated by ID

    public ArticalResponseDto updateArtical(long articalId,ArticalRequestDto articalRequestDto){
        String usernameFromToken= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(usernameFromToken)
                .orElseThrow((()->new IllegalStateException("your not Authorized ")));
        Artical artical = articalRepository.findById(articalId)
                .orElseThrow(()->new NoSuchElementException("No such Artical Exist in the DB"));
        if(!artical.getUser().getId().equals(user.getId()) ){
            throw  new IllegalStateException("your UnAuthorized to perform this task");
        }
        artical.setTitle(articalRequestDto.getTitle());
        artical.setContent(articalRequestDto.getContent());
        Artical updatedArtical=articalRepository.save(artical);
        ArticalResponseDto response = new ArticalResponseDto();
        response.setContent(updatedArtical.getContent());
        response.setTitle(updatedArtical.getTitle());
        response.setAuthorName(updatedArtical.getUser().getUsername());
        response.setArticalId(updatedArtical.getId());
        return response;
    }

    public Page<ArticalResponseDto> getAllUserArticals(int page , int size){
        String LoggedUsername= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(LoggedUsername).
                orElseThrow(()->new IllegalStateException("Your UnAuthorized Please Do Login"));

        Pageable pageable= PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Artical> articalPage= articalRepository.findByUserId(user.getId(),pageable);

        return articalPage.map((artical)-> {
            ArticalResponseDto articalResponseDto= new ArticalResponseDto();
            articalResponseDto.setArticalId(artical.getId());
            articalResponseDto.setAuthorName(artical.getUser().getUsername());
            articalResponseDto.setContent(artical.getContent());
            articalResponseDto.setTitle(artical.getTitle());
            return articalResponseDto;
        });


    }

    public Page<ArticalResponseDto> getAllArtical(int page , int size){
        Pageable pageable= PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Artical> articals= articalRepository.findAll(pageable);

        return articals.map((artical -> {
            ArticalResponseDto articalResponseDto= new ArticalResponseDto();
            articalResponseDto.setTitle(artical.getTitle());
            articalResponseDto.setContent(artical.getContent());
            articalResponseDto.setAuthorName(artical.getUser().getUsername());
            articalResponseDto.setArticalId(artical.getId());
            return articalResponseDto;
        }));
    }













    
}