package example.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.domain.model.Session;
import example.domain.model.UserAuthDetails;
import example.domain.model.pack.*;
import example.domain.model.word.Word;
import example.presentation.form.PackListForm;
import example.presentation.form.SearchWordsForm;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping
public class DashboardController {

    private final PackService packService;
    private final LikeService likeService;
    private final Session session;

    @GetMapping("/top")
    public String show(Model model,SearchWordsForm searchWordsForm,@AuthenticationPrincipal UserAuthDetails userAuthDetails) {
        
        List<Pack> packList= packService.getPackList();

        List<PackListForm> packs = new ArrayList<>();

        for(Pack pack:packList){

            List<String> words = new ArrayList<>();
            boolean isUsed = false;
            boolean isLiked = false;
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            long packid = pack.getId().getValue();
            for(long id:session.getShuffleList().getSelectIds()){
                if(packid == id){
                    isUsed = true;
                }
            }

            if(userAuthDetails != null){
            for(Pack likeList:likeService.readByUserId(userAuthDetails.getUserId())){
                if(packid == likeList.getId().getValue()){
                    isLiked = true;
                }
            }
        }

            packs.add(new PackListForm(pack.getId().getValue(), pack.getTitle().getValue(),words,pack.getUserName().getValue(),isUsed,isLiked));

        }

        searchWordsForm.setWord(session.getSearchWord());
        model.addAttribute("packs", packs);
        model.addAttribute("searchWordsForm",searchWordsForm);

        return "top";
    }

    @PostMapping("packs/search")
    public String searchPacksByWord(Model model,SearchWordsForm searchWordsForm,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        session.setSearchWord(searchWordsForm.getWord());

        List<Pack> packList= packService.getPackListBySearchWord(searchWordsForm.getWord());
        List<PackListForm> packs = new ArrayList<>();

        for(Pack pack:packList){

            List<String> words = new ArrayList<>();
            /** ここで使用状態リセットされるじゃん */
            boolean isUsed = false;
            boolean isLiked = false;
            for(Word word:pack.getWords()){
                words.add(word.getCharacterString().toString());
            }

            long packid = pack.getId().getValue();
            for(long id:session.getShuffleList().getSelectIds()){
                if(packid == id){
                    isUsed = true;
                }
            }

            if(userAuthDetails != null){
            for(Pack likeList:likeService.readByUserId(userAuthDetails.getUserId())){
                if(packid == likeList.getId().getValue()){
                    isLiked = true;
                }
            }
            }

            packs.add(new PackListForm(pack.getId().getValue(), pack.getTitle().getValue(),words,pack.getUserName().getValue(),isUsed,isLiked));

        }

        model.addAttribute("packs", packs);



        return "top";

    }
}
