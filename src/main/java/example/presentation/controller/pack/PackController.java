package example.presentation.controller.pack;

import java.util.ArrayList;
import java.util.List;

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
import example.domain.model.Session;
import example.domain.model.pack.*;
import example.presentation.helper.PackToPackFormHelper;

/**pack詳細 */

@Controller
@RequestMapping("packs/detail/{packId}")
public class PackController {

    @Autowired
    PackService packService;

    @Autowired
    LikeService likeService;

    @Autowired
    Session session;
    @Autowired
    PackToPackFormHelper toPackForm;

    @GetMapping
    public String packDetail(Model model,@PathVariable(value = "packId") PackId packId,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        Pack pack = packService.getPack(packId);
        if(pack == null){
            return "redirect:/top";
        }

        List<Pack> packList = new ArrayList<>();
        packList.add(pack);
        
        model.addAttribute("pack",toPackForm.toPackFormList(packList, session, userAuthDetails, likeService).get(0));
        return "packs/packDetail";
    }


}
