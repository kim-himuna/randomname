package example.infrastructure.datasource.pack;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import example.domain.model.pack.*;
import example.domain.model.user.UserId;
import example.domain.model.word.*;
import example.infrastructure.mybatisEntity.*;

@Mapper
public interface tmpPackMapper {

    /**R */
    Pack selectOne(@Param("packId") PackId packId);
    List<Word> selectWordsInPack(@Param("packId") PackId packId);
    List<Pack> selectAll();
    PackId registerNew();
    List<Long> selectIdsByWord(String word);
    List<Pack> selectPacksByTitle(String word);
    List<Pack> selectPacksByIds(@Param("ids")List<Long> ids);
    List<Pack> selectPacksByUserId(@Param("userId")UserId userId);

    /**C */
    void insertPack(PackEntity packEntity);
    void insertWords(@Param("wordList") List<WordEntity> wordList);

    /**U */
    void updatePack(PackEntity packEntity);
    void updateWords(@Param("wordList") List<WordEntity> wordList);

    /**D */
    void deletePack(PackId packId);
    void deleteWord(WordId wordId);
    void deleteWordsInPack(PackId packId);
}
