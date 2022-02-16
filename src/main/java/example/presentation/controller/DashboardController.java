package example.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import example.application.service.PackService;
import example.domain.model.ShuffleSession;
import example.domain.model.pack.*;
import example.domain.model.word.Word;
import example.presentation.form.PackListForm;
import example.presentation.form.SearchWordsForm;



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
            boolean isUsed = false;
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            long packid = pack.getId().getValue();
            for(long id:shuffleSession.getShuffleList().getSelectIds()){
                if(packid == id){
                    isUsed = true;
                }
            }

            packs.add(new PackListForm(pack.getId().getValue(), pack.getTitle().getValue(),words,isUsed));

        }

        SearchWordsForm searchWordsForm = new SearchWordsForm();
        model.addAttribute("packs", packs);
        model.addAttribute("searchWordsForm",searchWordsForm);

        return "/index";
    }

    @PostMapping("packs/search")
    public String searchPacksByWord(Model model,SearchWordsForm searchWordsForm){

        List<Pack> packList= packService.getPackListBySearchWord(searchWordsForm.getWord());
        List<PackListForm> packs = new ArrayList<>();

        for(Pack pack:packList){

            List<String> words = new ArrayList<>();
            /** ここで使用状態リセットされるじゃん */
            boolean isUsed = false;
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            long packid = pack.getId().getValue();
            for(long id:shuffleSession.getShuffleList().getSelectIds()){
                if(packid == id){
                    isUsed = true;
                }
            }

            packs.add(new PackListForm(pack.getId().getValue(), pack.getTitle().getValue(),words,isUsed));

        }

        model.addAttribute("packs", packs);



        return "/index";

    }
}
