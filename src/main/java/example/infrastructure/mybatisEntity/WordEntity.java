package example.infrastructure.mybatisEntity;

import example.domain.model.word.*;
import example.domain.model.pack.*;

public class WordEntity {
    private WordId wordId;
    private CharacterString characterString;
    private PackId packId;

    public WordEntity(WordId wordId,PackId packId,CharacterString characterString){
        this.wordId = wordId;
        this.packId = packId;
        this.characterString = characterString;
    }

    public WordEntity(PackId packId,CharacterString characterString){
        this.packId = packId;
        this.characterString = characterString;
    }

    public WordId getWordId(){
        return wordId;
    }

    public CharacterString getCharacterString(){
        return characterString;
    }

    public PackId getPackId(){
        return packId;
    }
}
