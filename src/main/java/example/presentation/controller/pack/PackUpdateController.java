package example.presentation.controller.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import example.application.service.PackService;
import example.application.service.UserAuthDetails;
import example.domain.model.pack.*;
import example.domain.model.word.Word;
import example.presentation.coordinator.pack.PackRecordCoordinator;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;

@Controller
@RequestMapping("packs/{packId}/update")
@SessionAttributes({"packForm"})
public class PackUpdateController {

    @Autowired
    PackService packService;

    @Autowired
    PackRecordCoordinator packRecordCoordinator;


    @GetMapping
    public String open(@PathVariable(value = "packId") PackId packId, Model model){

        Pack pack = packService.getPack(packId);
        PackForm packForm = new PackForm();
        packForm.setId(packId.getValue());
        packForm.setTitle(pack.getTitle().toString());

        List<WordForm> words = new ArrayList<>();
        for(Word word:pack.getWords()){
            words.add(new WordForm(word.getId().getValue(),word.getCharacterString().getValue()));    
        }

        packForm.setWords(words);

        model.addAttribute("packForm",packForm);
   
        return "packs/update/form";
    }

    @PostMapping("register")
    public String registerThenRedirect(@PathVariable(value = "packId") PackId packId,@Validated @ModelAttribute("packForm") PackForm packForm, BindingResult result,SessionStatus status,@AuthenticationPrincipal UserAuthDetails userAuthDetails){

        if (result.hasErrors()) {
            packForm.setId(packId.getValue());
            return "packs/update/form";
        }
        
        Pack pack = packRecordCoordinator.packCoordinate(packForm,userAuthDetails.getUserId(),userAuthDetails.getUserName());

        packService.updatePack(pack);
        status.setComplete();

        return "redirect:/packs/detail/" + packId;
    }
}
