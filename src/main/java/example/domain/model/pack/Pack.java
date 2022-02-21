package example.domain.model.pack;

import java.util.List;

import example.domain.model.user.UserId;
import example.domain.model.user.UserName;
import example.domain.model.word.Word;
import lombok.Getter;

@Getter
public class Pack{
    PackId id;
    PackTitle title;
    List<Word> words;
    UserId userId;
    UserName userName;

    @Deprecated
    public Pack(){}

    public Pack(PackId id,PackTitle title,List<Word> words,UserId userId,UserName userName){
        this.id = id;
        this.title = title;
        this.words = words;
        this.userId = userId;
        this.userName = userName;
    }

}
