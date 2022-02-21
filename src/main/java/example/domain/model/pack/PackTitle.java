package example.domain.model.pack;

import javax.validation.constraints.Size;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PackTitle {

    /**ドメイン層でこれ必要？formで良くない？ */
    final int maxrange = 10;
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = maxrange, message = "{max}文字以内で入力してください。")
    private String value;

    public PackTitle(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
