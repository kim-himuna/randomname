package example.presentation.form;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class PackForm {

    private long id;

    final int maxrange = 10;
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    public String title;
    
    @Valid
    public List<WordForm> words;

    public PackForm(){}

    public PackForm(long id,String title,List<WordForm> words){
        this.id = id;
        this.title = title;
        this.words = words;
    }
}