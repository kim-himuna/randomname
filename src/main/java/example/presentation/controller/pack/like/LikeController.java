package example.presentation.controller.pack.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.LikeService;
import example.domain.model.UserAuthDetails;
import example.domain.model.like.Like;
import example.domain.model.pack.PackId;

@Controller
@RequestMapping("like/{packId}/{fromPage}")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping
    public String likePack(Model model,@AuthenticationPrincipal UserAuthDetails userAuthDetails,@PathVariable(value = "packId") PackId packId,@PathVariable(value = "fromPage") String fromPage){

        likeService.create(new Like(null, userAuthDetails.getUserId(), packId));

        if(fromPage.equals("top")){
            return "redirect:/top";
        }else if(fromPage.equals("packsDetail")){
            return "redirect:/packs/detail/" + packId.getValue();
        }else if(fromPage.equals("productlist")){
            return "redirect:/users/product/from" + packId.getValue();
        }else if(fromPage.equals("usersDetail")){
            return "redirect:/users/detail";
        }else if(fromPage.equals("shuffleDetail")){
            return "redirect:/packs/shuffle/detail";
        }

        return "redirect:/top";
    }
    
}
