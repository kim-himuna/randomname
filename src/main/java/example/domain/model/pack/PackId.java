package example.domain.model.pack;

import lombok.Getter;

@Getter
public class PackId {
    private Long value;

    public PackId(Long value){
        this.value = value;
    }

    public static PackId from(String value) {
        return new PackId(Long.parseLong(value));
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
