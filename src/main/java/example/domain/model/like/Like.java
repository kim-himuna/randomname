package example.domain.model.like;

import example.domain.model.pack.PackId;
import example.domain.model.user.UserId;
import lombok.Data;

@Data
public class Like {
    private LikeId likeId;
    private UserId userId;
    private PackId packId;

    @Deprecated
    public Like(){}
    
    public Like(LikeId likeId,UserId userId,PackId packId){
        this.likeId = likeId;
        this.userId = userId;
        this.packId = packId;
    }
}
