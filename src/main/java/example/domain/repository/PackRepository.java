package example.domain.repository;

import java.util.List;

import example.domain.model.pack.*;
import example.domain.model.word.*;

public interface PackRepository{
    /**R */
    Pack getPack(PackId packId);
    List<Pack> getPackList();
    List<Pack> getPackListByTitle(String word);
    List<Pack> getPackListByWord(String word);
    List<Word> getWordsInPack(PackId packId);
    PackId registerNew();
    

    /**C */
    PackId register(Pack pack);

    /**U */
    void update(Pack pack);
    
    /**D */
    void deletePack(PackId packId);
    void deleteWord(WordId wordId);
    void deleteWordsInPack(PackId packId);
    
}
