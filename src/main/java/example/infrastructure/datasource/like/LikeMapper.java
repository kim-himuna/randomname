package example.infrastructure.datasource.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import example.domain.model.like.Like;
import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;
import example.infrastructure.mybatisEntity.LikeEntity;

@Mapper
public interface LikeMapper {
    List<Pack> selectPacksByUserId(UserId userId);
    void insert(LikeEntity likeEntity);
    void deleteByUserIdAndPackId(LikeEntity likeEntity);
    Like selectLikeByLike(Like like); 
}
