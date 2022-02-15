package example.domain.model.like;

public class LikeId {
    private Long value;

    public LikeId(Long value){
        this.value = value;
    }

    public static LikeId from(String value) {
        return new LikeId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value){
        this.value = value;
    }
}
