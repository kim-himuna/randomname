package example.domain.model.user;

import lombok.Getter;

@Getter
public class UserPassword {
    private String value;

    public UserPassword(String value){
        this.value = value;
    }

}
