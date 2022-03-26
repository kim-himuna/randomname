package example.domain.model.word;

import lombok.Getter;

@Getter
public class Word {
    WordId id;
    CharacterString characterString;

    @Deprecated
    public Word(){}

    public Word(WordId id, CharacterString characterString){
        this.id = id;
        this.characterString = characterString;
    }
}
