package example.presentation.form;

import java.util.List;

import example.domain.model.pack.Pack;
import lombok.Data;

@Data
public class ShuffleSelectPacks {
    private List<Pack> selectPacks;

    public ShuffleSelectPacks(List<Pack> selectPacks){
        this.selectPacks = selectPacks;
    };
}
