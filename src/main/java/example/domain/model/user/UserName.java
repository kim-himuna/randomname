package example.domain.model.user;

import lombok.Getter;

@Getter
public class UserName {
    private String value;

    public UserName(String value){
        this.value = value;

    }

}
