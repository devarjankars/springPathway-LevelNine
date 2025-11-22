package com.SoloLeveling.LevelNine.Controllers.userController;


import com.SoloLeveling.LevelNine.Service.ArticalService.ArticalServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/atical")
public class articalController {

    private final ArticalServices articalServices;
    public articalController(ArticalServices articalServices){
        this.articalServices=articalServices;
    }


    @PostMapping("/post")
    public ResponseEntity<?> createNewArtical( )


}
