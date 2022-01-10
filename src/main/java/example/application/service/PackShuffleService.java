package example.application.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.application.repository.PackRepository;
import example.domain.model.ShuffleList;
import example.domain.model.pack.*;
import example.domain.model.word.*;

@Service
public class PackShuffleService {

    @Autowired
    private PackRepository packRepository;

    public String shuffleResult(ShuffleList shuffleList){

        String shuffleResultString ="";
        String shuffleResultStringAfter ="";
        
        Random random = new Random();

        ArrayList<Word> shuffleWordList = new ArrayList<>();
        for(Long packId:shuffleList.getSelectIds()){
            shuffleWordList.addAll(packRepository.getWordsInPack(new PackId(packId)));
        }
        
        int randomrange = shuffleWordList.size();
        int wordCount =shuffleList.getWordCount();
        int wordSize = shuffleList.getWordSize();

        for(int i = 0; i < wordCount; i++){
            shuffleResultStringAfter = shuffleResultString + shuffleWordList.get(random.nextInt(randomrange)).getCharacterString().getValue();
            if(shuffleResultStringAfter.length() > wordSize){
                break;
            }
            shuffleResultString = shuffleResultStringAfter;
        }

        if(shuffleResultString.length() == 0){
            shuffleResultString = "名前が生成出来ませんでした。使用単語が全て最大文字数を超えている可能性があります。「シャッフル詳細」から最大文字数を確認してください。";
        }

        return shuffleResultString;

    }
}
