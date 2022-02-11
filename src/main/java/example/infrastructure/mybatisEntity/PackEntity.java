package example.infrastructure.mybatisEntity;

import example.domain.model.pack.*;

public class PackEntity {
    private PackTitle packTitle;
    private PackId packId;

    public PackEntity(PackId packId,PackTitle packTitle){
        this.packId = packId;
        this.packTitle = packTitle;
    }

    public PackEntity(PackTitle packTitle){
        this.packTitle = packTitle;
    }

    public PackTitle getTitle(){
        return packTitle;
    }

    public PackId getId(){
        return packId;
    }
}
