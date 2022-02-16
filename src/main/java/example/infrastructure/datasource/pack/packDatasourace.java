package example.infrastructure.datasource.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import example.domain.model.pack.*;
import example.domain.model.word.*;
import example.domain.repository.PackRepository;
import example.infrastructure.mybatisEntity.*;

@Repository
public class packDatasourace implements PackRepository {
    @Autowired
    packMapper mapper;

    @Override
    public Pack getPack(PackId packId){
        return mapper.selectOne(packId);
    }
    
    @Override
    public List<Pack> getPackList(){
        return mapper.selectAll();
    }

    @Override
    public List<Word> getWordsInPack(PackId packId) {
        return mapper.selectWordsInPack(packId);
    }

    @Override
    public PackId registerNew(){
        return mapper.registerNew();        
    }

    @Override
    public PackId register(Pack pack){
        mapper.insertPack(new PackEntity(pack.getTitle()));
        PackId packId = mapper.registerNew();
        List<WordEntity> wordList = new ArrayList<WordEntity>();
        for(Word word: pack.getWords()){
            wordList.add(new WordEntity(packId,word.getCharacterString()));
        }
        mapper.insertWords(wordList);

        return packId;
    }
    
    @Override
    public void update(Pack pack){
        PackId packId = pack.getId();
        mapper.updatePack(new PackEntity(packId, pack.getTitle()));

        List<WordEntity> wordList = new ArrayList<WordEntity>();
        for(Word word: pack.getWords()){
            wordList.add(new WordEntity(word.getId(),packId,word.getCharacterString()));
        }
        mapper.updateWords(wordList);
    }

    @Override
    public void deletePack(PackId packId){
        mapper.deletePack(packId);
    }
/*
    @Override
    public void registerWord(Word word) {
        mapper.insertWord(new WordEntity(word.getPackId(), word.getCharacterString()));
    }

    @Override
    public void updateWord(Word word) {
        mapper.updateWord(new WordEntity(word.getWordId(),word.getPackId(), word.getCharacterString()));
    }
*/
    @Override
    public void deleteWord(WordId wordId) {
        mapper.deleteWord(wordId);
    }

    @Override
    public void deleteWordsInPack(PackId packId) {
        mapper.deleteWordsInPack(packId);

        
    }

    @Override
    public List<Pack> getPackListByTitle(String word) {
        return mapper.selectPacksByTitle(word);
    }

    @Override
    public List<Pack> getPackListByWord(String word) {
        return mapper.selectPacksByWord(word);
    }

}
