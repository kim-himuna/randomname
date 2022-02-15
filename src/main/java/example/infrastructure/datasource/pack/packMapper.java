package example.infrastructure.datasource.pack;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import example.domain.model.pack.*;
import example.domain.model.word.*;
import example.infrastructure.mybatisEntity.*;

@Mapper
public interface packMapper {

    /**R */
    Pack selectOne(@Param("packId") PackId packId);
    List<Word> selectWordsInPack(@Param("packId") PackId packId);
    List<Pack> selectAll();
    PackId registerNew();
    List<Pack> selectPacksByWord(String word);

    /**C */
    void insertPack(PackEntity packEntity);
    void insertWord(WordEntity wordEntity);

    /**U */
    void updatePack(PackEntity packEntity);
    void updateWord(WordEntity wordEntity);

    /**D */
    void deletePack(PackId packId);
    void deleteWord(WordId wordId);
    void deleteWordsInPack(PackId packId);
}
