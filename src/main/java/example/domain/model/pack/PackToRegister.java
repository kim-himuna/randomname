package example.domain.model.pack;

import java.util.List;

import example.domain.model.word.WordToRegister;


public class PackToRegister{
    PackId id;
    PackTitle title;
    List<WordToRegister> words;

    public PackToRegister(PackTitle title){
        this.title = title;
    }

    public PackToRegister(PackId id,PackTitle title,List<WordToRegister> words){
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

    public List<WordToRegister> getWords(){
        return words;
    }
}
