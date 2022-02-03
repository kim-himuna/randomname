package example.infrastructure.datasource.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import example.application.repository.UserRepository;
import example.domain.model.user.User;
import example.domain.model.user.UserName;

@Repository
public class UserDatasource implements UserRepository{
    @Autowired
    UserMapper mapper;

    @Override
    public User getUserByName(UserName username) {
        return mapper.findOne(username);
    }
    
    
}
