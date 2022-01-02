package example.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.application.service.PackService;
import example.application.service.PackShuffleService;
import example.domain.model.ShuffleList;
import example.domain.model.ShuffleSession;
import example.domain.model.pack.Pack;
import example.domain.model.pack.PackId;
import example.presentation.form.ShuffleDetailForm;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("packs/shuffle")
public class PackShuffleController {

    @Autowired
    private PackShuffleService packShuffleService;

    @Autowired
    private PackService packService;

    @Autowired
    private ShuffleSession shuffleSession;

    @RequestMapping("/{packId}/listAdd")
    public String ShuffleListAdd(@PathVariable long packId,RedirectAttributes redirectAttrs){
        /**shuffleListに追加 */
        ShuffleList shuffleList = shuffleSession.getShuffleList();
        shuffleList.selectIds.add(packId);

        shuffleSession.setShuffleList(shuffleList);
        redirectAttrs.addFlashAttribute("shuffleSession",shuffleSession);

        return "redirect:/";
    }

    @RequestMapping("/{packId}/listRemove")
    public String ShuffleListremove(@PathVariable PackId packId,RedirectAttributes redirectAttrs){
        ShuffleList shuffleList = shuffleSession.getShuffleList();
        shuffleList.selectIds.remove(shuffleList.selectIds.indexOf(packId.getValue()));
        return "redirect:/";
    }

    @GetMapping()
    public String WordShuffle(Model model){
        
        if(shuffleSession.getShuffleList() != null){

            if(shuffleSession.getShuffleList().getSelectIds().size() != 0){        
                String word = packShuffleService.shuffleResult(shuffleSession.getShuffleList());
                System.out.println(word);
                model.addAttribute("word", word);

                return "packs/shuffle/shuffleResult";
            }
            
            
        }
        
        System.out.println("redirectpacks");

        return "redirect:/";
    }



    @GetMapping("detail")
    public String ShuffleDetail(Model model){
        ShuffleDetailForm shuffleDetailForm = new ShuffleDetailForm(shuffleSession.getShuffleList().getWordSize(), shuffleSession.getShuffleList().getWordCount());
        model.addAttribute("shuffleDetailForm",shuffleDetailForm);
        model.addAttribute("shuffleSession",shuffleSession);

        List<Pack> selectPack = new ArrayList<>();

        for(Long packId:shuffleSession.getShuffleList().getSelectIds()){
            selectPack.add(packService.getPack(new PackId(packId)));
        }

        shuffleDetailForm.setSelectPack(selectPack);
        return "packs/shuffle/shuffleDetail";
    }

    @Transactional
    @PostMapping("detail/update")
    public String shuffleDetailUpdate(@Validated @ModelAttribute ShuffleDetailForm shuffleDetailForm, BindingResult result,RedirectAttributes redirectAttrs){

        if (result.hasErrors()) {
            return "packs/shuffle/shuffleDetail";
        }

        ShuffleList shuffleList = shuffleSession.getShuffleList();

        shuffleList.setWordCount(shuffleDetailForm.getWordCount());
        shuffleList.setWordSize(shuffleDetailForm.getWordSize());

        shuffleSession.setShuffleList(shuffleList);

        redirectAttrs.addFlashAttribute("shuffleSession",shuffleSession);

        return "redirect:/";
    }

    @GetMapping("listReset")
    public String shuffleListReset(RedirectAttributes redirectAttrs){

        shuffleSession.getShuffleList().clearSelectIds();
        redirectAttrs.addFlashAttribute("shuffleSession",shuffleSession);

        return "forward:/packs/shuffle/detail";
    }
}