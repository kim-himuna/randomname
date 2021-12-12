package example.application.coordinator.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.application.service.PackService;
import example.domain.model.pack.PackId;
import example.domain.model.pack.PackTitle;
import example.domain.model.pack.PackToRegister;
import example.domain.model.word.CharacterString;
import example.domain.model.word.WordToRegister;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;



@Service
public class PackRecordCoordinator {
    @Autowired
    PackService packService;

    public PackId register(PackForm packForm){

        PackToRegister packToRegister = new PackToRegister(new PackTitle(packForm.getTitle()));
        packService.registerPack(packToRegister);
        PackId packId = packService.registerNew();

        for(WordForm word:packForm.wordsForm){
            packService.registerWord(new WordToRegister(packId,new CharacterString(word.word)));
        }

        return packId;
    }
}
