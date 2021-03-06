package example.presentation.form;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ShuffleDetailForm {

    /**文字数のリミット */
    @NotNull(message = "整数を入力してください")
    @Max(20)
    @Min(1)
    private Integer wordSize;

    /**単語数のリミット */
    @NotNull(message = "整数を入力してください")
    @Max(5)
    @Min(1)
    private Integer wordCount;
}
