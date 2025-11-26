package com.SoloLeveling.LevelNine.Controllers.articalController;


import com.SoloLeveling.LevelNine.DTOs.ArticalRequestDto;
import com.SoloLeveling.LevelNine.Service.ArticalService.ArticalServices;
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


        return null;

    }


}
