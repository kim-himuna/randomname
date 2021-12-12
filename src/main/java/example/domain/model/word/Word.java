package example.domain.model.word;

public class Word {
    WordId id;
    CharacterString characterString;

    @Deprecated
    public Word(){}

    public Word(WordId id, CharacterString characterString){
        this.id = id;
        this.characterString = characterString;
    }

    public WordId getId(){
        return id;
    }

    public CharacterString getCharacterString(){
        return characterString;
    }
}
