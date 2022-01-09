package example.presentation.form;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class ShuffleDetailForm {

    /**文字数のリミット */
    @Max(20)
    @Min(1)
    private int wordSize;

    /**単語数のリミット */
    @Max(5)
    @Min(1)
    private int wordCount;



    public ShuffleDetailForm(int wordSize,int wordCount){
        this.wordSize = wordSize;
        this.wordCount = wordCount;
    }

}
