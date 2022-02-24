package example.domain.repository;

import java.util.List;


import example.domain.model.like.Like;
import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;

public interface LikeRepository {
    List<Pack> selectPacksByUserId(UserId userId);
    void create(Like like);
    void deleteByUserIdAndPackId(Like like);
    Like getLikeByLike(Like like);

}
