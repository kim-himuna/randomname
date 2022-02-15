package example.infrastructure.datasource.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import example.domain.model.user.User;
import example.domain.model.user.UserName;
import example.domain.repository.UserRepository;

@Repository
public class UserDatasource implements UserRepository{
    @Autowired
    UserMapper mapper;

    @Override
    public User getUserByName(UserName username) {
        return mapper.selectOne(username);
    }

    @Override
    public void registerUser(User user) {
        mapper.insertUser(user);
    }
}
