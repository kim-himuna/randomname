package example.application.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.domain.model.pack.*;
import example.domain.model.user.UserId;
import example.domain.repository.PackRepository;


@Service
@Transactional
public class PackService{

    @Autowired
    private PackRepository packRepository;

    public List<Pack> getPackList(){
        return packRepository.getPackList();
    }

    
    public List<Pack> getPackListByTitle(String word){
        return packRepository.getPackListByTitle(word);
    }

    public List<Pack> getPackListByWord(String word){
        return packRepository.getPackListByWord(word);
    }
    public List<Pack> getPackListBySearchWord(String word){
        List<Pack> packs = packRepository.getPackListByTitle(word);
    
        List<Pack> packsword = packRepository.getPackListByWord(word);
        if(packsword != null){
            packs.addAll(packsword);
        }
        return packs;
    }

    public List<Pack> getPackListByCreatorId(UserId userId){
        return packRepository.getPackListByUserId(userId);
    }


    public List<Pack> getLikedPackList(){
        return packRepository.getPackList();
    }

    public PackId register(Pack pack){
        PackId packId = packRepository.register(pack);
        return packId;
    }

    public Pack getPack(PackId packId) {
        return packRepository.getPack(packId);
    }

    public PackId updatePack(Pack pack){
        packRepository.update(pack);

        return pack.getId();
    }

    public PackId deletePack(PackId packId){
    
        packRepository.deleteWordsInPack(packId);
        
        packRepository.deletePack(packId);

        return packId;
    }
}
