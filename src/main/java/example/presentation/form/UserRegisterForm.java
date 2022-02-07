package example.presentation.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegisterForm {
    
    /** さすがにパスワードとかをpublicはまずい気がするから調べて調整 */
    @NotBlank(message = "入力してください")
    @Size(max = 12, message = "{max}文字以内で入力してください。")
    public String userName;

    @NotBlank(message = "入力してください")
    @Size(max = 12, message = "{max}文字以内で入力してください。")
    public String userPassword;

}
