package example.domain.repository;

import java.util.List;


import example.domain.model.like.Like;
import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;

public interface LikeRepository {
    public List<Pack> selectPacksByUserId(UserId userId);
    public void create(Like like);
}
