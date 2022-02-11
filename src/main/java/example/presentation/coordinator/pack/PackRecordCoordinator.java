/** formの変換処理を行っている．詰め替え用のクラスの名前はよくわからない*/

package example.presentation.coordinator.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import example.domain.model.pack.Pack;
import example.domain.model.pack.PackId;
import example.domain.model.pack.PackTitle;
import example.domain.model.word.CharacterString;
import example.domain.model.word.Word;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;

@Component
public class PackRecordCoordinator {


    public Pack packCoordinate(PackForm packForm){

        List<Word> words = new ArrayList<Word>();

        for(WordForm word:packForm.getWords()){
            words.add(new Word(null,new CharacterString(word.word)));
        }

        Pack pack = new Pack(new PackId(packForm.getId()),new PackTitle(packForm.getTitle()),words);
        return pack;
    }
}
