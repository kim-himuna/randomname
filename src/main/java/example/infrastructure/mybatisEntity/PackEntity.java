package example.infrastructure.mybatisEntity;

import example.domain.model.pack.*;

public class PackEntity {
    private PackTitle packTitle;
    private PackId id;

    public PackEntity(PackId id,PackTitle packTitle){
        this.id = id;
        this.packTitle = packTitle;
    }

    public PackEntity(PackTitle packTitle){
        this.packTitle = packTitle;
    }

    public PackTitle getTitle(){
        return packTitle;
    }

    public PackId getId(){
        return id;
    }
}
