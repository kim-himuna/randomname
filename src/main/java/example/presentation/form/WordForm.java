package example.presentation.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class WordForm {

    private long id;

    final int maxrange = 10;
    
    @NotBlank(message = "ワードを入力してください")
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    public String word;

    public WordForm(){}

    public WordForm(long id,String word){
        this.id = id;
        this.word = word;
    }
}
