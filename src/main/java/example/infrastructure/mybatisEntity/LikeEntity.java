package example.infrastructure.mybatisEntity;

import example.domain.model.like.LikeId;
import example.domain.model.pack.PackId;
import example.domain.model.user.UserId;
import lombok.Data;

@Data
public class LikeEntity {
    private LikeId likeId;
    private UserId userId;
    private PackId packId;
    
    @Deprecated
    public LikeEntity(){}

    public LikeEntity(LikeId likeId,UserId userId,PackId packId){
        this.likeId = likeId;
        this.userId = userId;
        this.packId = packId;

    }
}
