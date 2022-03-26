package example.domain.model.word;

import lombok.Getter;

@Getter
public class WordId {
    private Long value;

    public WordId(Long value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value.toString();
    }

}
