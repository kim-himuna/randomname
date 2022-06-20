package example.presentation.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.domain.model.Session;
import example.domain.model.ShuffleList;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestUseController {

    private final Session session;
    
    @PostMapping("uselist")
    public String resnsyu(@RequestParam long packid){

        String note = "";
        ShuffleList shuffleList = session.getShuffleList();
        System.out.println(packid);
        int index = shuffleList.getSelectIds().indexOf(packid);

        if(index == -1){
            if(shuffleList.getSelectIds().size()<20){
                shuffleList.selectIds.add(packid);
                session.setShuffleList(shuffleList);
                note = "使用キャンセル";
            }else{
                note = "使用";
            }
        }else{
            shuffleList.selectIds.remove(index);
            note = "使用";
        }

        return note;
    }
}
