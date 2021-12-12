package example.domain.model.pack;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

public class PackTitle {

    final int maxrange = 10;
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    private String value;

    public PackTitle(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
