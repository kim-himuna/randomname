package example.domain.model.pack;

import lombok.Getter;

@Getter
public class PackTitle {

    private String value;

    public PackTitle(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
