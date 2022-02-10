/** formの変換処理を行っている．詰め替え用のクラスの名前はよくわからない*/

package example.presentation.coordinator.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import example.domain.model.pack.PackTitle;
import example.domain.model.pack.PackToRegister;
import example.domain.model.word.CharacterString;
import example.domain.model.word.WordToRegister;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;

@Component
public class PackRecordCoordinator {


    public PackToRegister packCoordinate(PackForm packForm){

        List<WordToRegister> words = new ArrayList<WordToRegister>();

        for(WordForm word:packForm.wordsForm){
            words.add(new WordToRegister(null,new CharacterString(word.word)));
        }

        PackToRegister packToRegister = new PackToRegister(null,new PackTitle(packForm.getTitle()),words);
        return packToRegister;
    }
}
