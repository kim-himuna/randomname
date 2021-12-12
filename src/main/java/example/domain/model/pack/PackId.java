package example.domain.model.pack;

public class PackId {
    private Long value;

    public PackId(Long value){
        this.value = value;
    }

    public static PackId from(String value) {
        return new PackId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
