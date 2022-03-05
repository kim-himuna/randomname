package example.presentation.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.LikeService;
import example.application.service.PackService;
import example.application.service.UserAuthDetails;
import example.domain.model.Session;
import example.domain.model.pack.Pack;
import example.domain.model.pack.PackId;
import example.domain.model.word.Word;
import example.presentation.form.PackListForm;
import example.presentation.form.SearchWordsForm;
import example.presentation.helper.PackToPackFormHelper;


@Controller
@RequestMapping("users")
public class UserDetailsController {

    @Autowired
    PackService packService;
    @Autowired
    LikeService likeService;
    @Autowired
    Session session;
    @Autowired
    PackToPackFormHelper toPackForm;

    @GetMapping("/detail")
    public String userDetail(Model model,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        
        List<Pack> likedPacks = likeService.readByUserId(userAuthDetails.getUserId());
        
        model.addAttribute("likedPacks", toPackForm.toPackFormList(likedPacks, session, userAuthDetails, likeService));

        List<Pack> producedPacks = packService.getPackListByCreatorId(userAuthDetails.getUserId());
        model.addAttribute("producedPacks", toPackForm.toPackFormList(producedPacks, session, userAuthDetails, likeService));
        return "user/userDetail";
    }

    @RequestMapping("/product/from{packId}")
    public String product(Model model,@PathVariable("packId") PackId packId,SearchWordsForm searchWordsForm,@AuthenticationPrincipal UserAuthDetails userAuthDetails){
        Pack fromPack = packService.getPack(packId);
        List<Pack> products = packService.getPackListByCreatorId(fromPack.getUserId());

        List<PackListForm> packs = new ArrayList<>();

        for(Pack pack:products){

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
        model.addAttribute("creatorName", fromPack.getUserName().getValue());



        return "/user/productlist";
    }
    
}
