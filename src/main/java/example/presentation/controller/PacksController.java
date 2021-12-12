package example.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.PackService;
import example.domain.model.ShuffleSession;
import example.domain.model.pack.Pack;


/**pack一覧 */

@Controller
@RequestMapping("packs")
public class PacksController {

    @Autowired
    PackService packService;

    @Autowired
    ShuffleSession shuffleSession;

    @GetMapping
    String packs(Model model){
        List<Pack> packList= packService.getPackList();
        model.addAttribute("packs", packList);

        System.out.println(shuffleSession.getShuffleList().selectIds);

        return "packs/packList";
    }
    
}
