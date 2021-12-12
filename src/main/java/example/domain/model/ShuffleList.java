package example.domain.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;



@Data
public class ShuffleList implements Serializable{

    public List<Long> selectIds;

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

    
    public ShuffleList(){
        this.wordSize = 12;
        this.wordCount = 3;
        this.selectIds = new ArrayList<>();
    }

    public void clearSelectIds(){
        this.selectIds = new ArrayList<>();
    }
    
}
