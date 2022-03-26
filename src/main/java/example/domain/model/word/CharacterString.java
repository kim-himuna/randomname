package example.domain.model.word;

import lombok.Getter;

@Getter
public class CharacterString {

    private String value;

    public CharacterString(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
}
