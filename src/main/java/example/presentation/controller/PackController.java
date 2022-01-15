package example.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.PackService;
import example.domain.model.pack.*;

/**pack詳細 */

@Controller
@RequestMapping("packs/{packId}")
public class PackController {

    @Autowired
    PackService packService;

    @GetMapping
    public String packDetail(Model model,@PathVariable(value = "packId") PackId packId){
        Pack pack = packService.getPack(packId);
        if(pack == null){
            return "redirect:/";
        }
        /**出来ればここでviewModelにしたい */
        model.addAttribute("pack",pack);
        return "packs/packDetail";
    }
}
