package example.presentation.controller.pack;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.domain.model.Session;
import example.domain.model.UserAuthDetails;
import example.domain.model.pack.*;
import example.presentation.helper.PackToPackFormHelper;
import lombok.RequiredArgsConstructor;

/**pack詳細 */

@Controller
@RequiredArgsConstructor
@RequestMapping("packs/detail/{packId}")
public class PackController {

    private final PackService packService;
    private final LikeService likeService;
    private final Session session;
    private final PackToPackFormHelper toPackForm;

    @GetMapping
    public String packDetail(Model model,@PathVariable(value = "packId") PackId packId,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        Pack pack = packService.getPack(packId);
        if(pack == null){
            return "redirect:/top";
        }

        model.addAttribute("pack",toPackForm.toPackForm(pack, session, userAuthDetails, likeService));
        return "packs/packDetail";
    }


}
