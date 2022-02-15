package example.infrastructure.datasource.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;
import example.infrastructure.mybatisEntity.LikeEntity;

@Mapper
public interface LikeMapper {
    List<Pack> selectPacksByUserId(UserId userId);
    public void insert(LikeEntity likeEntity);
    List<Pack> selectByUserId(UserId userId);
    
}
