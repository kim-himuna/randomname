package example.infrastructure.mybatisEntity;

import example.domain.model.word.*;
import example.domain.model.pack.*;

public class WordEntity {
    private WordId id;
    private CharacterString characterString;
    private PackId packId;

    public WordEntity(WordId id,PackId packId,CharacterString characterString){
        this.id = id;
        this.packId = packId;
        this.characterString = characterString;
    }

    public WordEntity(PackId packId,CharacterString characterString){
        this.packId = packId;
        this.characterString = characterString;
    }

    public WordId getWordId(){
        return id;
    }

    public CharacterString getCharacterString(){
        return characterString;
    }

    public PackId getPackId(){
        return packId;
    }
}
