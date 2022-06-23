package example.presentation.controller.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.application.service.LikeService;
import example.domain.model.UserAuthDetails;
import example.domain.model.like.Like;
import example.domain.model.pack.PackId;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestLikeController {
    
    private final LikeService likeService;

    @PostMapping("like")
    public String like(@RequestParam long packid,@AuthenticationPrincipal UserAuthDetails userAuthDetails){

        String note = "お気に入り";
        System.out.println(packid);
        boolean liked  = likeService.create(new Like(null, userAuthDetails.getUserId(), new PackId(packid)));
        if(liked){
            note = "お気に入り解除";
        }
        return note;
        
    }

}
