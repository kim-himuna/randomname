package example.application.repository;

import java.util.List;

import example.domain.model.pack.*;
import example.domain.model.word.*;

public interface PackRepository{
    /**R */
    Pack getPack(PackId packId);
    List<Pack> getPackList();
    List<Word> getWordsInPack(PackId packId);
    PackId registerNew();

    /**C */
    void registerPack(PackToRegister packToRegister);
    void registerWord(WordToRegister wordToRegister);

    /**U */
    void updatePack(PackToRegister packToRegister);
    void updateWord(WordToRegister wordToRegister);
    
    /**D */
    void deletePack(PackId packId);
    void deleteWord(WordId wordId);
    void deleteWordsInPack(PackId packId);
    
}
