package example.presentation.form;

import javax.validation.constraints.Size;


public class SearchWordsForm {

    
    final int maxrange = 15;
    
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    public String word;

    public SearchWordsForm(){}

    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }
}
