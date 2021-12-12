package example.domain.model.pack;

import java.util.List;

import example.domain.model.word.Word;


public class Pack{
    PackId id;
    PackTitle title;
    List<Word> words;

    @Deprecated
    public Pack(){}

    public Pack(PackId id,PackTitle title,List<Word> words){
        this.id = id;
        this.title = title;
        this.words = words;
    }

    public PackId getId(){
        return id;
    }

    public PackTitle getTitle(){
        return title;
    }

    public List<Word> getWords(){
        return words;
    }
}
