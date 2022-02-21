package example.domain.model.user;

import lombok.Getter;

@Getter
public class UserId {
    private long value;

    public UserId(long value){
        this.value = value;
    }

}
