package example.domain.model.word;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CharacterString {
    
    final int maxrange = 10;
    @NotBlank
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    private String value;

    public CharacterString(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
}
