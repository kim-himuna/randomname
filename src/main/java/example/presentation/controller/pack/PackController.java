package example.presentation.controller.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.application.service.UserAuthDetails;
import example.domain.model.like.Like;
import example.domain.model.pack.*;

/**pack詳細 */

@Controller
@RequestMapping("packs/detail/{packId}")
public class PackController {

    @Autowired
    PackService packService;

    @Autowired
    LikeService likeService;

    @GetMapping
    public String packDetail(Model model,@PathVariable(value = "packId") PackId packId){
        Pack pack = packService.getPack(packId);
        if(pack == null){
            return "redirect:/top";
        }
        model.addAttribute("pack",pack);
        return "packs/packDetail";
    }

    @GetMapping("like")
    public String likePack(Model model,@AuthenticationPrincipal UserAuthDetails userAuthDetails,@PathVariable(value = "packId") PackId packId){

        likeService.create(new Like(null, userAuthDetails.getUserId(), packId));

        return "redirect:/packs/detail/{packId}";
    }
}
