package example.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.domain.model.like.Like;
import example.domain.model.pack.Pack;
import example.domain.model.user.UserId;
import example.domain.repository.LikeRepository;

@Service
@Transactional
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    public List<Pack> readByUserId(UserId userId){
        return likeRepository.selectPacksByUserId(userId);
    }
    
    public void create(Like like){

        if(likeRepository.getLikeByLike(like) == null){
            likeRepository.create(like);
        }else{
            likeRepository.deleteByUserIdAndPackId(like);
        }

    }
}
