package example.presentation.controller.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.application.service.PackShuffleService;
import example.domain.model.ShuffleList;
import example.domain.model.UserAuthDetails;
import example.domain.model.Session;
import example.domain.model.pack.Pack;
import example.domain.model.pack.PackId;
import example.presentation.form.ShuffleDetailForm;
import example.presentation.form.ShuffleSelectPacks;
import example.presentation.helper.PackToPackFormHelper;

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
    private LikeService likeService;

    @Autowired
    private Session session;

    @Autowired
    private PackToPackFormHelper toPackFormHelper;


    @RequestMapping("/{packId}/listAdd/{fromPage}")
    public String ShuffleListAdd(@PathVariable("packId") long packId,@PathVariable("fromPage") String fromPage,RedirectAttributes redirectAttrs){

        ShuffleList shuffleList = session.getShuffleList();

        /**選べるpack数を20にしてる */
        if(shuffleList.getSelectIds().size()<20){
            shuffleList.selectIds.add(packId);
            session.setShuffleList(shuffleList);
        }
        redirectAttrs.addFlashAttribute("session",session);

        if(fromPage.equals("top")){
            return "redirect:/top";
        }else if(fromPage.equals("packsDetail")){
            return "redirect:/packs/detail/" + packId;
        }else if(fromPage.equals("productlist")){
            return "redirect:/users/product/from" + packId;
        }else if(fromPage.equals("usersDetail")){
            return "redirect:/users/detail";
        }else if(fromPage.equals("shuffleDetail")){
            return "redirect:/packs/shuffle/detail";
        }

        return "redirect:/top";
    }

    @RequestMapping("/{packId}/listRemove/{fromPage}")
    public String ShuffleListRemoveFromTop(@PathVariable("packId") PackId packId,@PathVariable("fromPage") String fromPage,RedirectAttributes redirectAttrs){
        ShuffleList shuffleList = session.getShuffleList();
        shuffleList.selectIds.remove(shuffleList.selectIds.indexOf(packId.getValue()));


        if(fromPage.equals("top")){
            return "redirect:/top";
        }else if(fromPage.equals("packsDetail")){
            return "redirect:/packs/detail/" + packId;
        }else if(fromPage.equals("productlist")){
            return "redirect:/users/product/from" + packId;
        }else if(fromPage.equals("usersDetail")){
            return "redirect:/users/detail";
        }else if(fromPage.equals("shuffleDetail")){
            return "redirect:/packs/shuffle/detail";
        }

        return "redirect:/top";
    }


    @GetMapping()
    public String WordShuffle(Model model){

        String[] wordsResult = new String[10];

        if(session.getShuffleList().getSelectIds().size() != 0){
            for (int i = 0; i < 10; i++){
                wordsResult[i] = packShuffleService.shuffleResult(session.getShuffleList());
            }
        }

        model.addAttribute("wordsResult", wordsResult);
        return "packs/shuffle/shuffleResult";
    }



    @GetMapping("detail")
    public String ShuffleDetail(Model model,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        ShuffleDetailForm shuffleDetailForm = new ShuffleDetailForm();
        shuffleDetailForm.setWordSize(session.getShuffleList().getWordSize()); 
        shuffleDetailForm.setWordCount(session.getShuffleList().getWordCount());
        model.addAttribute("shuffleDetailForm",shuffleDetailForm);
        model.addAttribute("session",session);

        List<Pack> selectPacks = new ArrayList<>();

        for(Long packId:session.getShuffleList().getSelectIds()){
            selectPacks.add(packService.getPack(new PackId(packId)));
        }
        model.addAttribute("shuffleselectPacks", toPackFormHelper.toPackFormList(selectPacks, session, userAuthDetails, likeService));
        return "packs/shuffle/shuffleDetail";
    }

    @PostMapping("detail/update")
    public String shuffleDetailUpdate(@Validated @ModelAttribute("shuffleDetailForm") ShuffleDetailForm shuffleDetailForm, BindingResult result,Model model,RedirectAttributes redirectAttrs){
        if (result.hasErrors()) {
            List<Pack> selectPacks = new ArrayList<>();

            for(Long packId:session.getShuffleList().getSelectIds()){
                selectPacks.add(packService.getPack(new PackId(packId)));
            }

            ShuffleSelectPacks shuffleSelectPacks = new ShuffleSelectPacks(selectPacks);
            model.addAttribute("shuffleselectPacks", shuffleSelectPacks);
            return "packs/shuffle/shuffleDetail";
        }

        ShuffleList shuffleList = session.getShuffleList();

        shuffleList.setWordCount(shuffleDetailForm.getWordCount());
        shuffleList.setWordSize(shuffleDetailForm.getWordSize());

        session.setShuffleList(shuffleList);

        redirectAttrs.addFlashAttribute("session",session);

        return "redirect:/packs/shuffle/detail";
    }

    @GetMapping("listReset")
    public String shuffleListReset(RedirectAttributes redirectAttrs){

        session.getShuffleList().clearSelectIds();
        redirectAttrs.addFlashAttribute("session",session);

        return "forward:/packs/shuffle/detail";
    }
}