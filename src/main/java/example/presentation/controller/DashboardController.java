package example.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import example.application.service.PackService;
import example.domain.model.ShuffleSession;
import example.domain.model.pack.*;
import example.domain.model.word.Word;
import example.presentation.viewModel.PackViewModel;



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

        List<PackViewModel> packs = new ArrayList<>();

        for(Pack pack:packList){
            List<String> words = new ArrayList<>();
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            packs.add(new PackViewModel(pack.getId().getValue(), pack.getTitle().getValue(),words));
        }

        model.addAttribute("packs", packs);

        System.out.println(shuffleSession.getShuffleList().selectIds);
        return "/index";
    }
}
