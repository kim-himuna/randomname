package example.domain.model.word;

public class WordId {
    private Long value;

    public WordId(Long value){
        this.value = value;
    }

    public Long getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value.toString();
    }

}
