package example.infrastructure.mybatisEntity;

import example.domain.model.pack.*;
import example.domain.model.user.UserId;

public class PackEntity {
    private PackTitle packTitle;
    private PackId packId;
    private UserId userId;

    public PackEntity(PackId packId,PackTitle packTitle,UserId userId){
        this.packId = packId;
        this.packTitle = packTitle;
        this.userId = userId;
    }

    public PackEntity(PackTitle packTitle,UserId userId){
        this.packTitle = packTitle;
        this.userId = userId;
    }

    public PackTitle getTitle(){
        return packTitle;
    }

    public PackId getId(){
        return packId;
    }

    public UserId getUserId(){
        return userId;
    }
}
