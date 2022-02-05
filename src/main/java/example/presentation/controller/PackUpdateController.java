package example.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.PackService;
import example.domain.model.pack.*;
import example.domain.model.word.CharacterString;
import example.domain.model.word.WordToRegister;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;

@Controller
@RequestMapping("packs/{packId}/update")
public class PackUpdateController {

    @Autowired
    PackService packService;


    @GetMapping
    public String open(@PathVariable(value = "packId") PackId packId, Model model){

        Pack pack = packService.getPack(packId);
        PackForm packForm = new PackForm();
        packForm.setId(packId.getValue());
        packForm.setTitle(pack.getTitle().toString());

        WordForm[] words = new WordForm[5];
        for(int i = 0; i< 5; i++){
            words[i] = new WordForm(); 
            words[i].setWord(pack.getWords().get(i).getCharacterString().getValue()); 
        }

        packForm.setWordsForm(words);

        model.addAttribute("packForm",packForm);
   
        return "packs/update/form";
    }

    @Transactional
    @PostMapping("register")
    public String registerThenRedirect(@PathVariable(value = "packId") PackId packId,@Validated @ModelAttribute("packForm") PackForm packForm, BindingResult result, Model model){

        if (result.hasErrors()) {
            packForm.setId(packId.getValue());
            return "packs/update/form";
        }

        Pack pack = packService.getPack(packId);

        List<WordToRegister> words = new ArrayList<WordToRegister>();

        int i = 0;

        for(WordForm word:packForm.wordsForm){
            WordToRegister wordToRegister = new WordToRegister(packId,new CharacterString(word.getWord()));
            wordToRegister.setId(pack.getWords().get(i).getId());
            words.add(wordToRegister);
            i++;
        }

        PackToRegister packToRegister = new PackToRegister(packId,new PackTitle(packForm.getTitle()),words);

        packService.updatePack(packToRegister);

        return "redirect:/packs/detail/" + packId;
    }
}
