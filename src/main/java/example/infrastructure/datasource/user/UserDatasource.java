package example.infrastructure.datasource.user;


import org.springframework.stereotype.Repository;

import example.domain.model.user.User;
import example.domain.model.user.UserName;
import example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDatasource implements UserRepository{

    private final UserMapper mapper;

    @Override
    public User getUserByName(UserName username) {
        return mapper.selectOne(username);
    }

    @Override
    public void registerUser(User user) {
        mapper.insertUser(user);
    }
}
