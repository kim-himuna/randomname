package example.presentation.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import example.application.service.LikeService;
import example.application.service.UserAuthDetails;
import example.domain.model.Session;
import example.domain.model.pack.Pack;
import example.domain.model.word.Word;
import example.presentation.form.PackListForm;

@Component
public class PackToPackFormHelper {
    
    public List<PackListForm> toPackFormList(List<Pack> packList,Session session,UserAuthDetails userAuthDetails,LikeService likeService){
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

        return packs;

    }
}
