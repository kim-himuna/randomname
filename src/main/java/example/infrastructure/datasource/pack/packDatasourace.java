package example.infrastructure.datasource.pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import example.application.repository.PackRepository;
import example.domain.model.pack.*;
import example.domain.model.word.*;
import example.infrastructure.mybatisEntity.*;

@Repository
public class packDatasourace implements PackRepository {
    @Autowired
    packMapper mapper;

    @Override
    public Pack getPack(PackId packId){
        return mapper.findOne(packId);
    }
    
    @Override
    public List<Pack> getPackList(){
        return mapper.findAll();
    }

    @Override
    public List<Word> getWordsInPack(PackId packId) {
        return mapper.findWordsInPack(packId);
    }

    @Override
    public PackId registerNew(){
        return mapper.registerNew();        
    }

    @Override
    public void registerPack(PackToRegister packToRegister){
        mapper.insertPack(new PackEntity(packToRegister.getTitle()));
    }
    
    @Override
    public void updatePack(PackToRegister packToRegister){
        mapper.updatePack(new PackEntity(packToRegister.getId(), packToRegister.getTitle()));

    }

    @Override
    public void deletePack(PackId packId){
        mapper.deletePack(packId);

    }

    @Override
    public void registerWord(WordToRegister wordToRegister) {
        mapper.insertWord(new WordEntity(wordToRegister.getPackId(), wordToRegister.getCharacterString()));
    }

    @Override
    public void updateWord(WordToRegister wordToRegister) {
        mapper.updateWord(new WordEntity(wordToRegister.getWordId(),wordToRegister.getPackId(), wordToRegister.getCharacterString()));
    }

    @Override
    public void deleteWord(WordId wordId) {
        mapper.deleteWord(wordId);
    }

    @Override
    public void deleteWordsInPack(PackId packId) {
        mapper.deleteWordsInPack(packId);

        
    }

}
