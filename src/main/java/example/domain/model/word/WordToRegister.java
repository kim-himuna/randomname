package example.domain.model.word;

import example.domain.model.pack.*;

public class WordToRegister {
    private WordId id;
    private CharacterString characterString;
    private PackId packId;

    public WordToRegister(PackId packId,CharacterString characterString){
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