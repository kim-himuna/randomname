package example.infrastructure.datasource.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import example.domain.model.user.User;
import example.domain.model.user.UserName;

@Mapper
public interface UserMapper {
    User findOne(@Param("userName") UserName userName);
    void insertUser(User user);
}
