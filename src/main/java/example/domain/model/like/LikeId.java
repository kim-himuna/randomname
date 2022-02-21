package example.domain.model.like;

import lombok.Getter;

@Getter
public class LikeId {
    private Long value;

    public LikeId(Long value){
        this.value = value;
    }

    public static LikeId from(String value) {
        return new LikeId(Long.parseLong(value));
    }
}
