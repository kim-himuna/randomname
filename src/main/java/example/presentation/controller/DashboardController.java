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
import example.presentation.form.PackListForm;



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

        List<PackListForm> packs = new ArrayList<>();

        for(Pack pack:packList){

            List<String> words = new ArrayList<>();
            boolean using = false;
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            long packid = pack.getId().getValue();
            for(long id:shuffleSession.getShuffleList().getSelectIds()){
                if(packid == id){
                    using = true;
                }
            }

            packs.add(new PackListForm(pack.getId().getValue(), pack.getTitle().getValue(),words,using));

        }

        model.addAttribute("packs", packs);

        System.out.println(shuffleSession.getShuffleList().selectIds);
        return "/index";
    }
}
