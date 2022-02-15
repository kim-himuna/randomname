package example.presentation.controller.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.application.service.UserAuthDetails;
import example.domain.model.pack.Pack;


@Controller
@RequestMapping("users")
public class UserDetailsController {

    @Autowired
    PackService packService;
    @Autowired
    LikeService likeService;

    @GetMapping("/detail")
    public String userDetail(Model model,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        /**TODO フロントへの表示でpack使うかpackForm使うか検討 */
        List<Pack> likedPacks = likeService.readByUserId(userAuthDetails.getUserId());
        model.addAttribute("likedPacks", likedPacks);
        return "user/userDetail";
    }

    
}
