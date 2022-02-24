package example.infrastructure.datasource.like;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import example.domain.model.like.Like;
import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;
import example.domain.repository.LikeRepository;
import example.infrastructure.mybatisEntity.LikeEntity;

@Repository
public class LikeDatasource implements LikeRepository{


    @Autowired
    LikeMapper likeMapper;

    @Override
    public List<Pack> selectPacksByUserId(UserId userId) {
        List<Pack> likedList = new ArrayList<>();
        likedList = likeMapper.selectPacksByUserId(userId);
        
        return likedList;
    }

    @Override
    public void create(Like like) {

        likeMapper.insert(new LikeEntity(null,like.getUserId(),like.getPackId()));
    }

    @Override
    public void deleteByUserIdAndPackId(Like like) {

        likeMapper.deleteByUserIdAndPackId(new LikeEntity(null,like.getUserId(),like.getPackId()));
    }

    @Override
    public Like getLikeByLike(Like like) {

        return likeMapper.selectLikeByLike(like);
    }
    
}
