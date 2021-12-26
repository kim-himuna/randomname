package example.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import example.application.service.PackService;
import example.domain.model.ShuffleSession;
import example.domain.model.pack.*;



@Controller
@RequestMapping("/")
public class DashboardController {
    
    @Autowired
    PackService packService;

    @Autowired
    ShuffleSession shuffleSession;

    @GetMapping
    public String show(Model model) {
        
        List<Pack> packList= packService.getPackList();
        model.addAttribute("packs", packList);

        System.out.println(shuffleSession.getShuffleList().selectIds);
        return "/index";
    }
}
