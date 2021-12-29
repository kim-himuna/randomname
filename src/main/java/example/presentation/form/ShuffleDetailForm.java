package example.presentation.form;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import example.domain.model.pack.Pack;
import lombok.Data;

@Data
public class ShuffleDetailForm {

    /**文字数のリミット */
    @Max(20)
    @Min(1)
    @NotNull(message = "文字数を入力してください")
    private int wordSize;

    /**単語数のリミット */
    @Max(5)
    @Min(1)
    @NotNull(message = "単語数を入力してください")
    private int wordCount;

    private List<Pack> selectPack;

    public ShuffleDetailForm(int wordSize,int wordCount){
        this.wordSize = wordSize;
        this.wordCount = wordCount;
    }
    
}
