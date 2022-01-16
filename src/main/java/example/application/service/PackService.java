package example.application.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.application.repository.PackRepository;
import example.domain.model.pack.*;
import example.domain.model.word.*;


@Service
@Transactional
public class PackService{

    @Autowired
    private PackRepository packRepository;

    public List<Pack> getPackList(){
        return packRepository.getPackList();
    }
    
    public PackId registerPack(PackToRegister packToRegister){
        packRepository.registerPack(packToRegister);
        return packToRegister.getId();
    }

    public void registerWord(WordToRegister wordToRegister){
        packRepository.registerWord(wordToRegister);    
    }


    public Pack getPack(PackId packId) {
        return packRepository.getPack(packId);
    }

    public PackId registerNew(){
        return packRepository.registerNew();
    }


    public PackId updatePack(PackToRegister packToRegister){

        packRepository.updatePack(packToRegister);

        for(WordToRegister wordToRegister:packToRegister.getWords()){
            packRepository.updateWord(wordToRegister);    
        }

        return packToRegister.getId();
    }

    public PackId deletePack(PackId packId){
        packRepository.deleteWordsInPack(packId);
        packRepository.deletePack(packId);

        return packId;
    }
}
