package com.SoloLeveling.LevelNine.Controllers.articalController;


import com.SoloLeveling.LevelNine.DTOs.ArticalRequestDto;
import com.SoloLeveling.LevelNine.DTOs.ArticalResponseDto;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;
import com.SoloLeveling.LevelNine.Service.ArticalService.ArticalServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/artical")
public class ArticalController {

    private final ArticalServices articalServices;

    public ArticalController(ArticalServices articalServices) {
        this.articalServices = articalServices;

    }


    @PostMapping("/post")
    public ResponseEntity<?> postArtical(@RequestBody ArticalRequestDto articalRequestDto) {
        try {
            ArticalResponseDto articalResponseDto = articalServices.createArtical(articalRequestDto);

            return ResponseEntity.status(201).body(articalResponseDto);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get/{articalId}")
    public ResponseEntity<?> getArtical(@PathVariable long articalId) {
        try {

            ArticalResponseDto articalResponseDto= articalServices.getArticalById(articalId);
            return  ResponseEntity.status(200).body(articalResponseDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
    @DeleteMapping("/remove/{articalId}")
    public ResponseEntity<?> removeArticalById(@PathVariable long articalId){
        try {

            boolean isDeleted= articalServices.removeArticalById(articalId);
            if (!isDeleted){
                return  ResponseEntity.status(200).body("removed Artical sucessufuly");


            }
            return  ResponseEntity.status(500).body("Unable to Delete");

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/update/{articalId}")
    public ResponseEntity<?>updateArtical(@PathVariable long articalId, @RequestBody ArticalRequestDto articalRequestDto){
        try {
            System.out.println(articalRequestDto.getContent());
            ArticalResponseDto articalResponseDto= articalServices.updateArtical(articalId, articalRequestDto);
            return ResponseEntity.status(200).body(articalResponseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAllMyArticals")
    public ResponseEntity<?> getAllMyArticals(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {
             Page<ArticalResponseDto> articalResponseDto= articalServices.getAllUserArticals(page, size);
            return ResponseEntity.status(200).body(articalResponseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }


    }
     @GetMapping("/getAllPopularArticals")
    public ResponseEntity<?> getAllthelatestArtical(@RequestParam (defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try{
            Page<ArticalResponseDto> mostPopularArtical=articalServices.getAllArtical(page, size);
            if (mostPopularArtical.isEmpty()){
                return ResponseEntity.status(404).body("there is No Artical as of now");
            }
            return ResponseEntity.status(200).body(mostPopularArtical);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }


     }



}




