package com.SoloLeveling.LevelNine.Controllers.articalController;


import com.SoloLeveling.LevelNine.DTOs.ArticalRequestDto;
import com.SoloLeveling.LevelNine.DTOs.ArticalResponseDto;
import com.SoloLeveling.LevelNine.Service.ArticalService.ArticalServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artical")
public class ArticalController {

    private final  ArticalServices articalServices;

    public ArticalController(ArticalServices articalServices){
        this.articalServices=articalServices;

    }


    @PostMapping("/post")
    public ResponseEntity<?> postArtical(@RequestBody ArticalRequestDto articalRequestDto){
        try{
            ArticalResponseDto articalResponseDto= articalServices.createArtical(articalRequestDto);
            return ResponseEntity.status(201).body(articalResponseDto);

        }  catch(IllegalStateException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }


    }


}
